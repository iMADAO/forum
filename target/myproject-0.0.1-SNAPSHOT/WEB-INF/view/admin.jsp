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
<input type="hidden" value="${sessionScope.tipMess }" id="tipMess"/>
<c:remove var="tipMess"/>
<c:if test="${not empty sessionScope.admin }">
	${sessionScope.admin.adminName }
</c:if>
<c:if test="${not empty sessionScope.admin }">
	管理员已登录 <a href="<%=request.getContextPath() %>/adminLogout">退出登录</a>
</c:if>
<c:if test="${empty sessionScope.admin }">
	管理员未登录，请<a href="<%=request.getContextPath() %>/adminLogin">登录</a>后进行操作
</c:if>
<br/><br/>
管理员界面
<a href="<%=request.getContextPath()%>/theme?target=1">添加主题</a>
<table>
<c:forEach items="${requestScope.themeDTOList }" var="theme">
	<tr>
		<th>${theme.themeName }</th>
		<td><a href="<%=request.getContextPath()%>/category?target=1&themeId=${theme.id}">添加项</a></td>
		<td><a href="<%=request.getContextPath() %>/theme?target=2&themeId=${theme.id}">删除主题</a></td>
	</tr>
		<c:forEach items="${theme.categoryList }" var="category">
			<tr>
				<td><a href="<%=request.getContextPath() %>/postList?categoryId=${category.categoryId}">${category.categoryName }</a>
				<td><a href="<%=request.getContextPath()%>/category?target=2&categoryId=${category.categoryId}">删除</a></td>	
			</tr>
		</c:forEach>
	
</c:forEach>

</table>
</body>
</html>