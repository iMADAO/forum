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
	var tipMess = $("#tipMess").attr("value");
	if(tipMess!="null" && tipMess!=""){
		alert(tipMess);
	}
})
</script>
</head>
<body>
<input type="hidden" value=<%=request.getSession().getAttribute("tipMess") %> id="tipMess" />
<c:remove var="tipMess" scope="session"/>
<a href="<%=request.getContextPath() %>/index.jsp">返回主页</a>&nbsp&nbsp&nbsp
<c:if test="${not empty sessionScope.lastPage }">
	<a href="${sessionScope.lastPage}">返回上一页</a>
</c:if>
<br/><br/>
<form action="<%=request.getContextPath() %>/toLogin" method="Post">
<font color="red">${requestScope.ErrorMess }</font>
	<table>
		<tr>
			<td>用户名:</td>
			<td><input type="text" name="username" value="${requestScope.username }" /></td>
		</tr>
		<tr>
			<td>密码:</td>		
			<td><input type="password" name="password" /></td>
		</tr>
		<tr>
			<td>请输入验证码:</td>
			<td><input type="text" name="validateCode" /></td>
			<td><img alt="" src="verificationCode" id="img"></td>
			<td><button id="refresh">刷新</button></td>
		</tr>
		<tr>
			<td><input type="checkbox" name="save" checked="checked"/>保存用户名和密码</td>
		</tr>
		<tr>
			<td><input type="submit" value=提交 /></td>
		</tr>
	</table>
	<br/>
	<a href="<%=request.getContextPath() %>/register">注册</a>	
</form>
</body>
</html>