<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "com.smallus.host.model.vo.Host" %>
<%
	Host h=(Host)request.getAttribute("hostId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="<%=request.getContextPath()%>/js/jquery-3.7.0.min.js"></script>
<title>아이디중복확인</title>
<style>
	body.m-msgbd{
	background: #FDF8EB;
	}
	div#m-checkidcontainer{
		text-align:center;
		padding-top:50px;		
	}
	span#duplicated{
		color:red;
		font-weight:bolder;
	}
	button#m-duplicatebtn{
		width:80px;
		height:40px;
		font-weight: bold;
		background-color: #E8D6C3;
		border:0px;
		border-radius: 1rem;
		text-align:center;
		font-size: 0.6rem;
	}
	div#m-checkidcontainer input[type=submit]{
		width:80px;
		height:25px;
		font-weight: bold;
		background-color: #E8D6C3;
		border:0px;
		border-radius: 1rem;
		text-align:center;
		font-size: 0.6rem;
	}
</style>
</head>
<body class="m-msgbd">
	<div id="m-checkidcontainer">
		<%if(h==null){ %>
		[<span><%=request.getParameter("hostId") %></span>]님의 아이디는 사용 가능합니다.	
		<br><br>
		<button type="button" id="m-duplicatebtn">사용하기</button>
		<%}else{ %>
		[<span id="duplicated"><%=request.getParameter("hostId")%></span>]님의 아이디는 사용 중입니다.
		<br><br>
		<!-- 아이디 재입력창 구성 -->
		<form action="<%=request.getContextPath()%>/host/idDuplicate.do" method="get">
			<input type="text" name="hostId" id="hostId">
			<input type="submit" value="중복검사">
		</form>
		<%} %>
	</div>
	<script>
	$("#m-duplicatebtn").click(e=>{
		opener.document.querySelector("#hostId")
		.value="<%=request.getParameter("hostId")%>";
		close();
	});
	</script>
</body>
</html>