<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<%request.getSession().setAttribute("lastPage", request.getContextPath() + "/index.jsp"); %>
<c:if test="${empty sessionScope.user }">
	用户未登录
	<a href="<%=request.getContextPath() %>/toLogin">登录</a>
	<a href="<%=request.getContextPath()%>/register">注册</a><br/>
</c:if>
<c:if test="${not empty sessionScope.user }">
	用户已登录--${sessionScope.user.userName }<br/><br/>
	<a href="<%=request.getContextPath()%>/myPage">我的主页</a>
	
	<a href="<%=request.getContextPath() %>/logout">退出登录</a>
	<br/>
</c:if>
	<input type="hidden" id="registerFlag" value="${sessionScope.registerSuccess==null ? '' : sessionScope.registerSuccess }"/>
	<input type="hidden" id="retrieveFlag" value="${sessionScope.retrieveSuccess==null ? '' : sessionScope.retrieveSuccess }"/>
	<c:remove var="registerSuccess" scope="session"/>
	<c:remove var="retrieveSuccess" scope="session"/>
	<a href="forum">进入论坛</a> 
</body>
</html>