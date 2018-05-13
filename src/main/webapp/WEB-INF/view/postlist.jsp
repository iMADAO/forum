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
<span>主题贴---${requestScope.category.categoryName }</span>
<br/><br/>
<%request.getSession().setAttribute("lastPage", request.getContextPath() + "/postList?categoryId=" + request.getParameter("categoryId")); %>
<c:if test="${empty sessionScope.user }">
	用户未登录
	<a href="<%=request.getContextPath() %>/toLogin">登录</a>
	<a href="<%=request.getContextPath()%>/register">注册</a>
</c:if>
<c:if test="${not empty sessionScope.user }">
	用户已登录--${sessionScope.user.userName }
	&nbsp&nbsp&nbsp<a href="<%=request.getContextPath() %>/logout">退出登录</a>
</c:if>
&nbsp
<a href="<%=request.getContextPath()%>/forum">返回上一页</a>
<br/><br/>
<a href="<%=request.getContextPath() %>/addPost?categoryId=<%=request.getParameter("categoryId")%>">发帖</a><br/><br/>
<table>
<c:forEach items="${requestScope.postDTOList }" var="postDTO">
	<tr>
		<td><a href="<%=request.getContextPath() %>/post?postId=${postDTO.postId}">${postDTO.messageDTO.messContent }</a></td>
	</tr>
		<c:forEach items="${postDTO.messageDTO.picPathList }" var="picPath">
			<tr>
				<td><a href="<%=request.getContextPath() %>/post?postId=${postDTO.postId}"><img src="${picPath }" width="100"/></a><hr/></td>
			</tr>
		</c:forEach>
	

</c:forEach>
</table>
</body>
</html>