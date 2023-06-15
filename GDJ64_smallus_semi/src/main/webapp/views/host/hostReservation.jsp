<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/host/hostHeader.jsp"%>
<!--main-->
<div id="mainOpacity h-host-main">
	 <section class="h-main h-rsv">
     	<div class="h-main-title">
     		<h2>클래스 예약 관리</h2>
     			<!-- 예약 내역 상세 페이지로 이동 -->
                <!-- <div class="h-viewList"><a href="">+</a></div> -->
        </div><hr>
        <seciton class="h-class-list-container">
			<div class="h-class-list-rowContainer">
				<div class="h-class-list">
					<a href="<%=request.getContextPath()%>/views/host/hostClassSub.jsp">
						<img src="<%=request.getContextPath()%>/img/img-slide2-1.png">
					</a>
					<table>
						<tr>
							<th class="h-tbl-align-left">승인 상태</th>
							<th>판매 상태</th>
							<th></th>
							<th>클래스 ID</th>
						</tr>
						<tr>
							<td class="h-tbl-align-left" colspan="4">클래스 이름</td>
						</tr>
						<tr>
							<td class="h-tbl-align-left">신청 일</td>
							<td>YYYY-MM-DD</td>
							<td>승인 일</td>
							<td>YYYY-MM-DD</td>
						</tr>
						<tr>
							<td></td>
							<td><button>수정</button></td>
							<td><button>삭제</button></td>
							<td><button>더보기</button></td>
						</tr>
					</table>
				</div>
				<div class="h-class-list">
					<img src="<%=request.getContextPath()%>/img/img-slide2-1.png">
					<table>
						<tr>
							<th>승인 상태</th>
							<th>판매 상태</th>
							<th></th>
							<th>클래스 ID</th>
						</tr>
						<tr>
							<td colspan="4">클래스 이름</td>
						</tr>
						<tr>
							<td>신청 일</td>
							<td>YYYY-MM-DD</td>
							<td>승인 일</td>
							<td>YYYY-MM-DD</td>
						</tr>
						<tr>
							<td></td>
							<td><button>수정</button></td>
							<td><button>삭제</button></td>
							<td><button>더보기</button></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="h-class-list-rowContainer">
				<div class="h-class-list">
					<img src="<%=request.getContextPath()%>/img/img-slide2-1.png">
					<table>
						<tr>
							<th>승인 상태</th>
							<th>판매 상태</th>
							<th></th>
							<th>클래스 ID</th>
						</tr>
						<tr>
							<td colspan="4">클래스 이름</td>
						</tr>
						<tr>
							<td>신청 일</td>
							<td>YYYY-MM-DD</td>
							<td>승인 일</td>
							<td>YYYY-MM-DD</td>
						</tr>
						<tr>
							<td></td>
							<td><button>수정</button></td>
							<td><button>삭제</button></td>
							<td><button>더보기</button></td>
						</tr>
					</table>
				</div>
				<div class="h-class-list">
					<img src="<%=request.getContextPath()%>/img/img-slide2-1.png">
					<table>
						<tr>
							<th>승인 상태</th>
							<th>판매 상태</th>
							<th></th>
							<th>클래스 ID</th>
						</tr>
						<tr>
							<td colspan="4">클래스 이름</td>
						</tr>
						<tr>
							<td>신청 일</td>
							<td>YYYY-MM-DD</td>
							<td>승인 일</td>
							<td>YYYY-MM-DD</td>
						</tr>
						<tr>
							<td></td>
							<td><button>수정</button></td>
							<td><button>삭제</button></td>
							<td><button>더보기</button></td>
						</tr>
					</table>
				</div>
			</div>
		</section>
	</section>        
<%@ include file="/views/host/hostFooter.jsp"%>
        