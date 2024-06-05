<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="../default/header.jsp"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/daumPost.js"></script>
</head>
<body>
	<div align="center">
	<caption><font size="5">회원등록</font></caption>
	<form action="register" method="post">
		<table border="1">
			<tr><td>
				<input type="text" name="id" placeholder="아이디"><br>
				<input type="text" name="pw" placeholder="비밀번호"><br>
				<input type="text" id="addr1" name="addr" readonly placeholder="우편번호"><br>
				<input type="text" id="addr2" name="addr" readonly placeholder="주소"><br>
				<input type="text" id="addr3" name="addr" placeholder="상세주소">
				<button type="button" onclick="daumPost()">우편번호 검색</button>
				<br>
				<hr>
				<input type="submit" value="회원가입">
			</td></tr>
		</table>
	</form>
	</div>
</body>
<c:import url="../default/footer.jsp"/>
</html>





