package pers.hmm.shop.common.utils;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

/**
 * http请求工具类
 * @author zclever <304078606@qq.com>
 * 
 */
public class HttpUtil {
	
	public static String get(String url){
		return get(url,false);
	}
	
	/**
	 * 发送get请求
	 * @param url	链接地址
	 * @param isSSL	是否是https协议
	 * @return		返回响应字符串
	 */
	public static String get(String url, boolean isSSL) {

		String result = "";
		BufferedReader in = null;
		try {
			Properties prop = System.getProperties(); 
			prop.put("http.proxySet","true"); 
			prop.put("http.proxyHost","192.168.176.28"); 
			prop.put("http.proxyPort","80");
			prop.put("https.proxyHost","192.168.176.28"); 
			prop.put("https.proxyPort","80");
			prop.put("http.nonProxyHosts","192*|10*");
			
			String urlNameString = url;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			if (isSSL) {
				connection = (HttpsURLConnection) connection;
			}
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.setConnectTimeout(1000);
			connection.connect();
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
		} finally {
			// 使用finally块来关闭输入流
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 发送get请求 字符集utf-8
	 * @param url	链接地址
	 * @param isSSL	是否是https协议
	 * @return		返回响应字符串
	 */
	public static String get(String url, boolean isSSL,String str) {

		String result = "";
		BufferedReader in = null;
		try {
			/*
			Properties prop = System.getProperties(); 
			prop.put("proxySet","true"); 
			prop.put("proxyHost","10.5.3.9"); 
			prop.put("proxyPort","80");
			*/
			String urlNameString = url;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			if (isSSL) {
				connection = (HttpsURLConnection) connection;
			}
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.setConnectTimeout(1000);
			connection.connect();
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(),"UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
		} finally {
			// 使用finally块来关闭输入流
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * 发送post请求
	 * @param url
	 * @param params
	 * @param isSsl
	 * @return
	 */
	public static String post(String reqUrl, String params, boolean isSSL)
    {
        HttpURLConnection connection = null;
        String responseContent = null;
        try
        {
            URL url = new URL(reqUrl);
            connection = (HttpURLConnection) url.openConnection();
            if (isSSL) {
				connection = (HttpsURLConnection) connection;
			}
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(1000);
            connection.setDoOutput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false); 
            connection.connect();
            OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream(), "utf-8");  
            osw.write(params);  
            osw.flush();  
            osw.close();  

            InputStream in = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(in));
            String tempLine = rd.readLine();
            StringBuffer tempStr = new StringBuffer();
            String crlf=System.getProperty("line.separator");
            while (tempLine != null)
            {
                tempStr.append(tempLine);
                tempStr.append(crlf);
                tempLine = rd.readLine();
            }
            responseContent = tempStr.toString();
            rd.close();
            in.close();
        }
        catch (IOException e)
        {
        	System.out.println("发送POST请求出现异常！" + e);
        }
        finally
        {
            if (connection != null)
            {
                connection.disconnect();
            }
        }
        return responseContent;
    }
	
	/**
	 * 获取http请求体
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String getHttpBody(HttpServletRequest request) throws IOException{
		BufferedReader br = request.getReader();

		String str, wholeStr = "";
		while((str = br.readLine()) != null){
			wholeStr += str;
		}
		return wholeStr;
	}

	
	public static void main(String[] args) {
		String str = HttpUtil.post("https://api.mch.weixin.qq.com/pay/unifiedorder","<xml></xml>",true);
		System.out.println(str);
	}

}
