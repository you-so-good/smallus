<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/host/hostHeader.jsp"%>

<div id="mainOpacity">
	<section id="d-addClass">
		<br>
		<br>
		<h3>클래스 등록하기</h3>
		<div id="inputs">
			<p>클래스 이름</p>
			<input type="text" placeholder="    등록하실 클래스명을 입력해주세요.">
			<p>등록할 사진</p>
			<img
				src="https://img.freepik.com/free-icon/add-button-with-plus-symbol-in-a-black-circle_318-48599.jpg"
				width="200" height="200" alt="">
				<!-- 이미지 소스가 아닌 내장파일로 수정 -->
			<p>카테고리</p>
			<select>
				<option value="요리">요리</option>
				<option value="공예">공예</option>
				<option value="뷰티">뷰티</option>
				<option value="액티비티">액티비티</option>
			</select>
			<p>클래스 회당 인원제한</p>
			<input type="number" placeholder="    최대 인원수를 입력해주세요.">명
			<p>1인당 클래스 금액</p>
			<input type="number" placeholder="    금액을 입력해주세요.">원
			<p>주소</p>
			<input type="text" placeholder="    버튼으로 주소 입력" readonly>
			<button>주소 찾기</button>
			<br> <input type="text" placeholder="    상세 주소를 입력해주세요.">
			<p>제공사항</p>
			<input type="text" placeholder="    제공사항을 입력해주세요.">
			<p>유의사항</p>
			<input type="text" placeholder="    유의사항을 입력해주세요.">
			<p>준비물</p>
			<input type="text" placeholder="    준비물을 입력해주세요.">
			<p>상세 내용</p>
			<textarea name="" id="" cols="80" rows="10"
				placeholder="    글자는 최대 500자까지 작성 가능합니다."></textarea>
		</div>
		<br>
		<br>
		<button>다음(스케쥴 등록)</button>
		<br>
		<br>
		<br>
		<br>







		<%@ include file="/views/host/hostFooter.jsp"%>