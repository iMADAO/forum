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
<%request.getSession().setAttribute("lastPage", request.getContextPath() + "/reply?messageId" + request.getParameter("messageId")); %>
<c:if test="${empty sessionScope.user }">
	用户未登录
	<a href="<%=request.getContextPath() %>/toLogin">登录</a>
	<a href="<%=request.getContextPath()%>/register">注册</a><br/>
</c:if>
<c:if test="${not empty sessionScope.user }">
	${sessionScope.user.userName }
	<a href="<%=request.getContextPath() %>/logout">退出登录</a>
	<br/>
</c:if>
 <br/>
<form action="<%=request.getContextPath() %>/reply" method="post">
	<input type="hidden" name="messageId" value="<%=request.getParameter("messageId")%>">
	<input type="hidden" name="postId" value="<%=request.getParameter("postId") %>" />
<table>
	<tr>
		<td>回复内容:</td>
		<td><input type="text" name="replyContent"></td>
	</tr>

	<tr>
		<td><input type="submit" value="提交" /></td>
	</tr>
</table>

</form>

</body>
</html>