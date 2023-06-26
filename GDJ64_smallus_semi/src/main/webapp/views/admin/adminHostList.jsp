<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.smallus.host.model.vo.Host"%>
<%@ include file="/views/common/hostHeader.jsp"%>
<%
List<Host> HostList=(List)request.getAttribute("HostList"); 
%>
<body>
	<div id="mainOpacity h-host-main">
		<section class="h-main">
			<div class="h-main-title">
				<h2>호스트회원관리</h2>
			</div>
			<select id="m-selectHost" onchange="selectHost();">
				<option value="A">전체호스트회원</option>
				<option value="Y" <%=request.getParameter("hostSt")!=null&&request.getParameter("hostSt").equals("Y")?"selected":""%>>일반호스트회원</option>
				<option value="N" <%=request.getParameter("hostSt")!=null&&request.getParameter("hostSt").equals("N")?"selected":""%>>탈퇴한호스트회원</option>
			</select>
		</section>
		<section class="h-main h-main-rsvList">
			<div>
				<table id="h-main-rsv-tbl">
					<tr>
						<th>아이디 </th>
						<th>이름</th>
						<th>닉네임</th>
						<th>전화번호</th>
						<th>이메일</th>
						<th>마케팅수신동의</th>
						<th>회원상태</th>
						<th></th>
					</tr>
				<%if(HostList!=null&&!HostList.isEmpty()) {
					for(Host h:HostList){%>
					<tr>
						<td><%=h.getHostId()%></td>
						<td><%=h.getHostName()%></td>
						<th><%=h.getHostNickname()%></th>
						<td><%=h.getHostPhone()%></td>
						<td><%=h.getHostEmail()%></td>
						<td><%=h.getHostConsent()%></td>
						<td><%=h.getHostSt()%></td>
						<%if(h.getHostSt().equals("Y")){ %>
						<td><button onclick="deleteHost('<%=h.getHostId()%>');">삭제</button></td>
						<%}else{%>
							<td></td>
						<%}%>
					</tr>
					<%} %>
				<%}else{ %>
			        <tr>
                        <td colspan="8">조회된 호스트가 없습니다.</td>
                    </tr>
				<%} %>
				</table>
			</div>
		</section>
		<div class="pageBar">
		<%if(request.getAttribute("pageBar")!=null){%>
				<%=request.getAttribute("pageBar")%>
			<%} %>
		</div>
</div>
</body>
<script>
	const selectHost=()=>{
		let index = $("#m-selectHost option").index($("#m-selectHost option:selected"));
		let hostSt=$("#m-selectHost").val();
		if(index==0){
			location.replace('<%=request.getContextPath()%>/admin/HostListServlet.do');
		}else if(index==1){
			location.assign('<%=request.getContextPath()%>/admin/HostListSortServlet.do?hostSt='+hostSt);
		}else if(index==2){
			location.assign('<%=request.getContextPath()%>/admin/HostListSortServlet.do?hostSt='+hostSt);
		}
	}
	
	
	const deleteHost=(hostId)=>{
		location.assign("<%=request.getContextPath()%>/admin/HostDelete.do?hostId="+hostId);
	} 
</script>
<%@ include file="/views/common/footer.jsp"%>