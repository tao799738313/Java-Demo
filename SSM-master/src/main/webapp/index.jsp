<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>

<h1><a href="https://blog.csdn.net/qq_38225558/article/details/83387917">文件上传</a></h1>
<form action="/uploadFile" method="post" enctype="multipart/form-data">
    文件上传：<input type="file" name="file"><br/>
    <input type="submit"><br/>
</form>
<form action="/uploadFiles" method="post" enctype="multipart/form-data">
    多文件上传：<input type="file" name="files" multiple="multiple"><br/>
    <input type="submit"><br/>
</form>
<h1><a href="https://blog.csdn.net/javaloveiphone/article/details/53888363">base64文件上传</a></h1>
文件base64上传：<input type="file" name="files" onchange="getBase64(this.files[0])"><br/>
<button onclick="tijiao()">提交base64</button>
<br/>
<h1>文件下载</h1>
<a href="/download?fileName=微信图片_20190928085442.jpg">下载</a>
<a href="/download2?fileName=1.jpg">下载2</a>
<script src="./common/js/jquery.min.js"></script>

<script>
    var canshu = {
        file: ""
    }

    function getBase64(file) {
        canshu.fileName = file.name;
        canshu.size = file.size;
        canshu.type = file.type;
        var fileReader = new FileReader()
        fileReader.readAsDataURL(file);
        fileReader.onload = function (res) {
            canshu.file = res.target.result;
        }
    }

    function tijiao() {
        $.ajax({
            url: '/uploadBase64',
            data: canshu,
            type: 'post',
            success: function (res) {
                console.log(res)
            }
        })
    }
</script>

</body>
</html>
