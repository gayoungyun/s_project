<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script  type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/modal.js"></script>
<link href="<%=request.getContextPath()%>/resources/css/modal.css" rel="stylesheet">
</head>
<body onload="readData('${dto.writeNo }')">
<%@ include file="/WEB-INF/views/default/header.jsp" %>
<%@ include file="/WEB-INF/views/board/modal.jsp" %>
	<table border="1" align="center">
		<caption><font size="5"><b>개인 정보</b></font></caption>
		<tr>
			<th width="100">글 번호</th><td width="200">${dto.writeNo}</td>
			<th width="100">작성자</th><td width="200">${dto.id}</td>
		</tr>
		<tr>
			<th>제목</th><td>${dto.title}</td>
			<th>등록일자</th><td>${dto.savedate}</td>
		</tr>
		<tr>
			<th>내용</th><td>${dto.content}</td>
			<td colspan="2">
				
					<img width="200px" height="100px"
					src="download?fileName=${dto.imageFileName }">

			</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
			<c:if test="${loginUser == dto.id }">
				<button type="button" onclick="location.href='modify_form?writeNo=${dto.writeNo}'">수정</button>
				<button type="button" onclick="location.href='delete?writeNo=${dto.writeNo}&fileName=${dto.imageFileName}'">삭제</button>
			</c:if>
				<button type="button" onclick="slideClick('${dto.writeNo}')">답글</button>
				<button type="button" onclick="location.href='boardAllList'">목록이동</button>
			</td>
		</tr>
	</table>
	<div id="data"></div>
</body>
<c:import url="../default/footer.jsp"/>
</html>





