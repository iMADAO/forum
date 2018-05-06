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
已有帐号 <a href="<%=request.getContextPath()%>/toLogin">登录</a>
<br/><br/>
<c:if test="${not empty requestScope.ErrorMess }">
	${requestScope.ErrorMess }
</c:if>

<%request.removeAttribute("ErrorMess"); %>
<form action="register" method="post">
<table>
	<tr>
		<td>姓名:</td>
		<td><input type="text" name="username"/></td>
	</tr>
	<tr>
		<td>密码</td>
		<td><input type="password" name="password"></td>
	</tr>
	<tr>
		<td>确认密码</td>
		<td><input type="password" name="repeatPassword"></td>
	</tr>
	<tr>
		<td>邮箱:</td>
		<td><input type="text" name="email"></td>
	</tr>
	<tr>
		<td>验证码</td>
		<td><input type="text" name="verificationCode"></td>
		<td><img alt="" src="verificationCode"/></td>
		<td><Button>刷新</Button></td>
	</tr>
	<tr><td><input type="submit" value="提交"></td></tr>
</table>
</form>
</body>
</html>