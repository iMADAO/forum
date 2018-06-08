<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
body
{
      margin-left:auto;
      margin-right:auto;
      width:80%;
   }
</style>
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
<h4>详情页</h4>
<%request.getSession().setAttribute("lastPage", request.getContextPath() + "/post?postId=" + request.getParameter("postId") ); %>
<c:if test="${empty sessionScope.user }">
	用户未登录
	<a href="<%=request.getContextPath() %>/toLogin">登录</a>
	<a href="<%=request.getContextPath()%>/register">注册</a><br/>
</c:if>
<c:if test="${not empty sessionScope.user }">
	用户已登录--<a href="<%=request.getContextPath()%>/myPage">${sessionScope.user.userName }</a>
	<a href="<%=request.getContextPath() %>/logout">退出登录</a>
</c:if>
<a href="<%=request.getContextPath() %>/index">主页</a>
<br/><br/>
<div id="d" name="d">
<c:forEach items="${requestScope.page.content }" var="messageDTO">
	${messageDTO.postOrder }楼: <br/>
	${messageDTO.username }:
	${messageDTO.messContent }
	<br/><br/>
	<c:forEach items="${messageDTO.picPathList }" var="picPath">
		<img src="${picPath }" width="200"/>
	</c:forEach>
	<br/>
	用户: ${messageDTO.username } &nbsp发表于: ${messageDTO.createTime } <br/>
	<br/>
	&nbsp&nbsp&nbsp&nbsp&nbsp
		<c:forEach items="${messageDTO.replyList }" var="reply">
			用户: ${reply.username }
			回复内容: ${reply.replyContent }
			时间： ${reply.createTime }
			<br/>
		</c:forEach>
	<br/>
	<a href="<%=request.getContextPath() %>/reply?messageId=${messageDTO.messageId}&postId=<%=request.getParameter("postId")%>">回复</a>
	<hr/>
</c:forEach>
<br/>
当前第${requestScope.page.pageNo }页 共${requestScope.page.totalPage }页
<a href="<%=request.getContextPath() %>/post?postId=<%=request.getParameter("postId")%>&pageNo=1&pageSize=${requestScope.page.pageSize }">首页</a>
<c:forEach items="${requestScope.page.navigatepageNums }" var="num">
	<a href="<%=request.getContextPath() %>/post?postId=<%=request.getParameter("postId")%>&pageNo=${num }&pageSize=${requestScope.page.pageSize }">${num }</a>
</c:forEach>
<a href="<%=request.getContextPath() %>/post?postId=<%=request.getParameter("postId")%>&pageNo=${requestScope.page.totalPage }&pageSize=${requestScope.page.pageSize }">末页</a>

<a href="<%=request.getContextPath() %>/appendMessage?postId=<%=request.getParameter("postId") %>">我要发言</a>
</div>
</body>
</html>