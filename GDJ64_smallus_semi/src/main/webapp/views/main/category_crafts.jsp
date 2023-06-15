<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/views/common/mainHeader.jsp"%>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/category/category.css"/>
    <div style="text-align: center;" id="img">
        <img src="<%=request.getContextPath()%>/img/category_main/craft1.jpg" alt="re1" width="1300px" height="600px" title="내 손으로 작은 바...">
    </div>
    
    <section id="main">
        <h2 style="font-size: 24px; margin: 3% 15%;">BEST CLASS</h2>
    <!-- ajax 통해서 넣을 예약 테이블 -->
    
    <div class="s_category_crafts" style="display: flex">
        <div class="craft1">
            <img src="<%=request.getContextPath()%>/img/category_main/craft1.jpg" alt="" width="300px" height="300px" title="내 손으로 작은 바..."">
            <h5>내 손으로 작은 바다 만들기</h5>
            <p style="font-size: 8px">공예 | 서울시 광진구</p>
            <p style="font-size: 8px">유리구슬 | 2023.06.15</p>
        </div>
        <div class="craft2">
            <img src="<%=request.getContextPath()%>/img/category_main/craft2.jpg" alt="" width="300px" height="300px" title="우리 집 반려동물...">
            <h5>우리 집 반려동물 만들기</h5>
            <p style="font-size: 8px">공예 | 경기도 수원시</p>
            <p style="font-size: 8px">야니얀 | 2023.06.15</p>
        </div>
        <div class="craft3">
            <img src="<%=request.getContextPath()%>/img/category_main/craft3.jpg" alt="" width="300px" height="300px" title="나만의 썬캐쳐 만들...">
            <h5>나만의 썬캐쳐 만들기</h5>
            <p style="font-size: 8px">공예 | 서울시 광진구</p>
            <p style="font-size: 8px">라라의 공방일기 | 2023.06.15</p>
        </div>
        <div class="craft4">
            <img src="<%=request.getContextPath()%>/img/category_main/craft4.jpg" alt="" width="300px" height="300px"title="나만의 특별한 은반...">
            <h5>나만의 특별한 은반지 만들기</h5>
            <p style="font-size: 8px">공예 | 서울시 마포구</p>
            <p style="font-size: 8px">시엔 | 2023.06.15</p>
        </div>
    </div>

    <h2 style="font-size: 24px; margin: 3% 15%;">NEW CLASS</h2>
    <div class="s_category_crafts" style="display: flex">
        <div class="product">
            <img src="<%=request.getContextPath()%>/img/category_main/craft5.jpg" alt="" width="300px" height="300px" title="심플하면서 특별한 ..." id="preview">
            <h5>심플하면서 특별한 가죽 지감 만들기</h5>
            <p style="font-size: 8px">공예 | 서울시 광진구</p>
            <p style="font-size: 8px">니트에이 | 2023.06.15</p>
        </div>
        <div class="product">
            <img src="<%=request.getContextPath()%>/img/category_main/craft6.png" alt="" width="300px" height="300px" title="우리 집 안에 작은 ...">
            <h5>우리 집 안에 작은 바다 만들기</h5>
            <p style="font-size: 8px">공예 | 경기도 시흥시</p>
            <p style="font-size: 8px">솔메 | 2023.06.15</p>
        </div>
        <div class="product">
            <img src="<%=request.getContextPath()%>/img/category_main/craft7.jpg" alt="" width="300px" height="300px" title="아기자기한 도자기 ...">
            <h5>아기자기한 도자기 그릇 만들기</h5>
            <p style="font-size: 8px">공예 | 서울시 마포구</p>
            <p style="font-size: 8px">공예하는 언니 | 2023.06.15</p>
        </div>
        <div class="product">
            <img src="<%=request.getContextPath()%>/img/category_main/craft8.png" alt="" width="300px" height="300px" title="스테인 글라스로 나...">
            <h5>스테인 글라스로 나만의 소품 만들기</h5>
            <p style="font-size: 8px">공예 | 서울시 강동구</p>
            <p style="font-size: 8px">오월 | 2023.06.15</p>
        </div>
    </div>

    <h2 style="font-size: 24px; margin: 3% 15%;">ALL CLASS</h2>
</div>

    
    <div id="mainOpacity">
        <section id="banner">
        </section>
        
        <section id="main">
        </section>
<%@ include file="/views/common/footer.jsp"%>