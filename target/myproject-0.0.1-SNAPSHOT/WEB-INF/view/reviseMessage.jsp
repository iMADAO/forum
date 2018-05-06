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
修改信息
${requestScope.target }
<form action="<%=request.getContextPath() %>/reviseMessage" method="post">
<input type="hidden" value="${requestScope.target}" name="target">
<table>
	<tr>
		<td>用户名:</td>
		<td><input type="text" value="${sessionScope.user.userName }" name="username" <%=request.getAttribute("target").equals("username")? "" : "disabled='disabled'"%> /"></td>
	</tr>
	<tr>
		<td>邮箱:</td>
		<td><input type="text" value="${sessionScope.user.email }" name="email" <%=request.getAttribute("target").equals("email")? "" : "disabled='disabled'"%>></td>
	</tr>
	<tr><td><input type="submit" value="提交" /></td></tr>
</table>
</form>
</body>
</html>