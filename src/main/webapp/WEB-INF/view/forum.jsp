<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.project.bean.BarThemeDTO" %>
<%@ page import="java.util.List" %>
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
<%request.getSession().setAttribute("lastPage", request.getContextPath() + "/forum"); %>

<h4>论坛1</h4>

<c:if test="${empty sessionScope.user }">
	用户未登录
	<a href="<%=request.getContextPath() %>/toLogin">登录</a>
	<a href="<%=request.getContextPath()%>/register">注册</a><br/>
</c:if>
<c:if test="${not empty sessionScope.user }">
	用户已登录----${sessionScope.user.userName }
	&nbsp&nbsp&nbsp<a href="<%=request.getContextPath() %>/logout">退出登录</a>
	<br/><br/>
</c:if>

<table>
<c:forEach items="${requestScope.themeDTOList }" var="theme">
	<tr>
		<th>${theme.themeName }</th>
		<c:forEach items="${theme.categoryList }" var="category">
			<td>&nbsp&nbsp<a href="<%=request.getContextPath() %>/postList?categoryId=${category.categoryId}">${category.categoryName }</a></td>
		</c:forEach>
	</tr>
	
</c:forEach>

</table>
<%=request.getContextPath()%>

</body>
</html>