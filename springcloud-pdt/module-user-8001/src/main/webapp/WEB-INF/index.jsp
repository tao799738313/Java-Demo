
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath() ;
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>FastDFS-Demo</title>
</head>
<body>

${msg }
<br>
<form action="<%=path%>/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file">
    <input type="submit" value="提交">
</form>

</body>
</html>