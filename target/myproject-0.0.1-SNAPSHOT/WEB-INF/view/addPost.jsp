<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery-1.3.1.js"></script>
<script type="text/javascript">

$(function(){
	var tipMess = $("#tipMess").val();
	if(tipMess!="null" & tipMess!=""){
		$("#tipMess").val("");
		alert(tipMess);
	}
	tipMess="";
})
</script>
</head>
<body>
<input type="hidden" value=<%=request.getSession().getAttribute("tipMess") %> id="tipMess" />
<c:remove var="tipMess" scope="session"/>

<form action="<%=request.getContextPath() %>/addPost" method="post" enctype="multipart/form-data">
<input type="hidden" value="<%=request.getParameter("categoryId")%>" name="categoryId"/>
	<table>
		<tr>
			<td>内容</td>
			<td><input type="text" name="content" /></td>
		</tr>
		<tr>
			<td>图片</td>
			<td><input type="file" name="uploadFile"/></td>
		</tr>
		<tr><td><input type="submit" value="提交"/></td></tr>
	</table>
</form>
</body>
</html>