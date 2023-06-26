<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.smallus.classes.model.vo.Classes" %>
<%
	List<Classes> classList=(List)request.getAttribute("classList"); 
	List<Classes> classListPass=(List)request.getAttribute("classListPass");
%>
<%@ include file="/views/common/hostHeader.jsp"%>
<style>
:root { -
	-btn-radius: 2rem; -
	-img-radius: 1rem; -
	-be-color: #F5E7D2; /* 연한 베이지 버튼 */ -
	-ye-color: #FBEFD3; /* 연한 노란색 버튼 */ -
	-dk-color: #E8D6C3; /*진한베이지 버튼 */ -
	-main-col-lt: #FFFCF5; /* main primary */ -
	-border-color: #F6E9DF; /*border  연한 컬러*/ -
	-font-small: 0.8rem; /* button 폰트 사이즈 */ -
	-btn-padding: 0.4rem 0.6rem; /* button padding 값 */ -
	-btn-bold: bolder;
}

.h-modal {
	position: fixed;
	z-index: 1;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: auto;
	background-color: rgba(0, 0, 0, 0.4);
	display: none;
}

.modal-content {
	background-color: #FFFBF5;
	margin: 15% auto;
	padding: 3rem;
	width: 40%;
	text-align:center;
}

.modal-content button {
	background-color:var(--be-color);
	padding: var(--btn-padding);
	font-size: var(--font-small);
	border-radius: var(--btn-radius);
	border: 0;
	margin-left:1rem;
	margin-top:2rem;
}

.modal-content button:hover, .modal-content button:focus {
	color: black;
	text-decoration: none;
	cursor: pointer;
}
</style>
<!--main-->
<div id="mainOpacity h-host-main">
	<!-- 내 클래스 보기 페이지 -->
	 <section class="h-main h-main-rsvList">
     	<div class="h-main-title">
     		<h3>내 클래스 보기</h3>
                <!-- <div class="h-viewList"><a href="">+</a></div> -->
                <select name="h-selectClassStatus" id="h-selectClassStatus" onchange="selectOption()">
                	<option>승인 상태(전체)</option>
                	<option value="W" <%=request.getParameter("passStatus")!=null&&request.getParameter("passStatus").equals("W")?"selected":""%>>승인 대기</option>
                	<option value="Y" <%=request.getParameter("passStatus")!=null&&request.getParameter("passStatus").equals("Y")?"selected":""%>>승인 완료</option>
                	<option value="N" <%=request.getParameter("passStatus")!=null&&request.getParameter("passStatus").equals("N")?"selected":""%>>승인 거절</option>
                </select>
        </div><hr>
        <!-- hostId를 통해서 가지고 온 클래스 리스트  -->
        <div class="h-class-list-container">
			<%if(classList!=null&&!classList.isEmpty()) {
				for(Classes c:classList){%>
				<div class="h-class-list h-class-list-n">
					<!-- 썸네일 이미지 클릭 혹은 더보기버튼 클릭으로 상세 페이지로 이동 -->
					<a href="<%=request.getContextPath()%>/class/viewHostClassDetail.do?classId=<%=c.getClassId()%>" id="h-class-list">
						<img src="<%=request.getContextPath()%>/img/<%=c.getClassThumbnail()%>">
						<input type="hidden" name="classId" class="h-classId" value="<%=c.getClassId()%>">
					</a>
					<table>
						<tr>
							<%if(c.getClassPassId().equals("W")){ %>
								<th class="h-tbl-align-left" class="h-class-pass-W">
										승인 대기
								</th>
								<th></th>
							<%}else if(c.getClassPassId().equals("N1") || c.getClassPassId().equals("N2") || c.getClassPassId().equals("N3")) {
								if(c.getClassPassId().equals("N1")){%>
									<th class="h-tbl-align-left" class="h-class-pass-N">승인 거절</th><th>내용 부실</th>
									<%}else if(c.getClassPassId().equals("N2")){%>
									<th class="h-tbl-align-left" class="h-class-pass-N">승인 거절</th><th>내용 부적절</th>
									<%}else if(c.getClassPassId().equals("N3")){%>
									<th class="h-tbl-align-left" class="h-class-pass-N">승인 거절</th><th>중복 등록</th>
									<%}%>
							<%} else if(c.getClassPassId().equals("Y")){%>
								<th class="h-tbl-align-left" class="h-class-pass-Y">
										승인 완료
								<th>
								<%} %> 
						<th colspan="3"><%=c.getClassId() %></th>
						</tr>
						<tr>
							<td class="h-tbl-align-left" colspan="4"><%=c.getClassTitle() %></td>
						</tr>
						<tr>
							<td class="h-tbl-align-left">신청 일</td>
							<td><%=c.getClassUpLoadDate() %></td>
							<td>승인 일</td>
								<%if(c.getClassPassId().equals("Y")){ %>
							<td>								
								<%= c.getClassPassDate()%>
							</td>
							<%}else{%>
							<td>-</td>
							<%} %>
						</tr>
						<tr>
							<td colspan="2"></td>
							<td><button>수정</button></td>
							<td><button onclick="deleteClass(<%=c.getClassId() %>)">삭제</button></td>
							<!-- <td><button class="h-deleteClass">삭제</button></td> -->
							<!--이미지 클릭 혹은 더보기버튼 클릭으로 상세 페이지로 이동 -->
						</tr>
					</table>
				</div>
				<%}
				}else if(classListPass!=null&&!classListPass.isEmpty()) {
					for(Classes c:classListPass){%>
				<div class="h-class-list h-class-pass-list">
					<!-- 썸네일 이미지 클릭 혹은 더보기버튼 클릭으로 상세 페이지로 이동 -->
					<a href="" id="h-class-list">
						<img src="<%=request.getContextPath()%>/img/<%=c.getClassThumbnail()%>">
						<input type="hidden" name="classId" class="h-classId" value="<%=c.getClassId()%>">
					</a>
					<table>
						<tr>
							<%if(c.getClassPassId().equals("W")){ %>
								<th class="h-tbl-align-left" class="h-class-pass-W">
										승인 대기
								</th>
								<th></th>
							<%} else if(c.getClassPassId().equals("Y")){%>
								<th class="h-tbl-align-left" class="h-class-pass-Y">
										승인 완료
								<th>
								<th>판매 중</th>
							<%}else if(c.getClassPassId().equals("N1") || c.getClassPassId().equals("N2") || c.getClassPassId().equals("N3")) {
									if(c.getClassPassId().equals("N1")){%>
									<th class="h-tbl-align-left" class="h-class-pass-N">승인 거절</th><th>내용 부실</th>
									<%}else if(c.getClassPassId().equals("N2")){%>
									<th class="h-tbl-align-left" class="h-class-pass-N">승인 거절</th><th>내용 부적절</th>
									<%}else if(c.getClassPassId().equals("N3")){%>
									<th class="h-tbl-align-left" class="h-class-pass-N">승인 거절</th><th>중복 등록</th>
									<%} 
								} %> 
 						<th colspan="2"><%=c.getClassId() %></th>
						</tr>
						<tr>
							<td class="h-tbl-align-left" colspan="4"><%=c.getClassTitle() %></td>
						</tr>
						<tr>
							<td class="h-tbl-align-left">신청 일</td>
							<td><%=c.getClassUpLoadDate() %></td>
							<td>승인 일</td>
								<%if(c.getClassPassId().equals("Y")){ %>
							<td>								
								<%= c.getClassPassDate()%>
							</td>
							<%}else{%>
							<td>-</td>
							<%} %>
						</tr>
						<tr>
							<td colspan="2"></td>
							<td><button>수정</button></td>
							<td><button class="h-deleteClass">삭제</button></td>
						</tr>
					</table>
				</div>
				<%}
				}else{ %>
					<div><h4>조회할 클래스가 없습니다</h4></div>
				<%} %>
        </div>
	            <div class="pageBar">
	            	<%=request.getAttribute("pageBar") %>
	            </div>
        </section>
		<div class="h-modal" style="display:hidden">
			<div class="modal-content">
				<h4>등록하신 클래스 세부 일정도 함께 삭제됩니다</h4>
				<h4>삭제 하시겠습니까?</h4>
				<button class="h-close-modal">삭제 취소</button>
				<button id="h-delClass">삭제하기</button>
			</div>
		</div>
	<script>
	
		// select 옵션 변경하면 이동하는 함
		function selectOption(){
			let index = $("#h-selectClassStatus option").index($("#h-selectClassStatus option:selected"));
			let div=$("#h-selectClassStatus option")
			// index =1 -> W / 2:Y/3:N
			//console.log(index);
			if(index==0){
				location.replace('<%=request.getContextPath()%>/class/viewHostClassList.do?hostId=<%=hostInfo.getHostId()%>');
			}else if(index==1){
				location.assign('<%=request.getContextPath()%>/class/sortingHostClassByPass.do?passStatus=W');
			}else if(index==2){
				location.assign('<%=request.getContextPath()%>/class/sortingHostClassByPass.do?passStatus=Y');
			}else if(index==3){
				location.assign('<%=request.getContextPath()%>/class/sortingHostClassByPass.do?passStatus=N');
			}
				$(".h-class-list-n").css('display','none');
				$(".h-class-pass-list").css('display','flex');
		}
		
		// 모달창 열기
		const deleteClass=(classId)=>{
			console.log(classId);
			$(".h-modal").css('display','block');
			document.body.css('overflow','hidden'); 
			classId=classId;
		}
		
		
		// 모달창 닫기
		$(".h-close-modal").click(e=>{
			$(".h-modal").css('display','none');
			document.body.css('overflow','auto'); 
		})
		
		$("#h-delClass").click(e=>{
			$.get(context+'/host/CheckDeleteClass.do?classId='+classId,function(data){
				if(data>0){
					alert('삭제 완료 ;)');
					$(".h-modal").css('display','none');
					document.body.css('overflow','auto'); 
				}else{
					alert('삭제 실패 ;<');
				}
			}); 
		});
		
		
	</script>       
<%@ include file="/views/common/hostFooter.jsp"%>
        