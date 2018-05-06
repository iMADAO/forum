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
<h4>我的主页</h4>
<table>
	<tr>
		<th>我的信息:</th>
	</tr>
	<tr>
		<td>用户id:</td>
		<td>${sessionScope.user.userId }</td>
		<td></td>
	</tr>
	<tr>
		<td>用户名</td>
		<td>${sessionScope.user.userName }</td>
		<td><a href="<%=request.getContextPath()%>/reviseMessage?target=username">修改用户名</a></td>
	</tr>
	<tr>
		<td>邮箱</td>
		<td>${sessionScope.user.email }</td>
		<td><a href="<%=request.getContextPath()%>/reviseMessage?target=email">修改邮箱</a></td>
	</tr>
</table>
<br/><br/>

我的贴子:&nbsp&nbsp&nbsp&nbsp<a href="<%=request.getContextPath()%>/forum">去发帖</a>
<br/><br/>
<c:if test="${empty requestScope.postDTOList }">
	空空如也
</c:if>
<c:forEach items="${requestScope.postDTOList }" var="postDTO">
	<c:if test="${not empty postDTO.messageDTO }">
		<a href="<%=request.getContextPath()%>/post?postId=${postDTO.postId }">${postDTO.messageDTO.messContent }</a>
		<br/>
		<c:forEach items="${postDTO.messageDTO.picPathList }" var="pic">
			<img src="${pic }" alt="not" width="100"/>
		</c:forEach>
		<br/>
		${postDTO.messageDTO.createTime}
		<br/>
		<br/>
	</c:if>
</c:forEach>

</body>
</html>