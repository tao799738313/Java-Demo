/*!
 * Image (upload) dialog plugin for Editor.md
 *
 * @file        image-dialog.js
 * @author      pandao
 * @version     1.3.4
 * @updateTime  2015-06-09
 * {@link       https://github.com/pandao/editor.md}
 * @license     MIT
 */

(function() {

    var factory = function (exports) {

		var pluginName   = "image-dialog";

		exports.fn.imageDialog = function() {

            var _this = this;
            var cm = this.cm;
            var lang = this.lang;
            var editor = this.editor;
            var settings = this.settings;
            var cursor = cm.getCursor();
            var selection = cm.getSelection();
            var imageLang = lang.dialog.image;
            var classPrefix = this.classPrefix;
            var iframeName = classPrefix + "image-iframe";
            var dialogName = classPrefix + pluginName, dialog;
            if (!settings.imageUpload) {
                  console.error("没有设置imageUpload")
                  return;
            }
            if(Object.prototype.toString.call(settings.imageUploadURL)!="[object String]"){
                console.error("imageUploadURL格式只能是字符串")
                return;
            }
            editor.find("."+classPrefix+"form").remove();
            cm.focus();
            var guid   = (new Date).getTime();
            var action = settings.imageUploadURL + (settings.imageUploadURL.indexOf("?") >= 0 ? "&" : "?") + "guid=" + guid;
            var str = "<form action=\"" + action +"\" target=\"" + iframeName + "\" method=\"post\" enctype=\"multipart/form-data\" class=\"" + classPrefix + "form\" style=\"display:none;\"><iframe name=\"" + iframeName + "\" id=\"" + iframeName + "\" guid=\"" + guid + "\"></iframe><div class=\"" + classPrefix + "file-input\"><input type=\"file\" name=\"" + classPrefix + "image-file\" accept=\"image/*\" /><input type=\"submit\" value=\"" + imageLang.uploadButton + "\" /></div></form>";
            editor.append(str);
            var fileInput  = editor.find("[name=\"" + classPrefix + "image-file\"]");
            fileInput.bind("change", function() {
                function submitHandler() {
                                var uploadIframe = document.getElementById(iframeName);
                                uploadIframe.onload = function() {
                                    var body = (uploadIframe.contentWindow ? uploadIframe.contentWindow : uploadIframe.contentDocument).document.body;
                                    var json = (body.innerText) ? body.innerText : ( (body.textContent) ? body.textContent : null);
                                    json = (typeof JSON.parse !== "undefined") ? JSON.parse(json) : eval("(" + json + ")");

                                      if (json.success === 1) {
                                          var url = json.url;
                                          if (url === "") {
                                              console.error("“”服务器返回值url字段为空");
                                              return false;
                                          }else{
                                              cm.replaceSelection("![](" + url + ")");
                                          }

                                      }else{
                                          console.error(json.message);
                                      }
                                    return false;
                                };
                }
                editor.find("."+classPrefix+"form").find("[type=\"submit\"]").bind("click", submitHandler).trigger("click");

            }).trigger("click");

        }
	};

	// CommonJS/Node.js
	if (typeof require === "function" && typeof exports === "object" && typeof module === "object")
    {
        module.exports = factory;
    }
	else if (typeof define === "function")  // AMD/CMD/Sea.js
    {
		if (define.amd) { // for Require.js

			define(["editormd"], function(editormd) {
                factory(editormd);
            });

		} else { // for Sea.js
			define(function(require) {
                var editormd = require("./../../editormd");
                factory(editormd);
            });
		}
	}
	else
	{
        factory(window.editormd);
	}

})();
