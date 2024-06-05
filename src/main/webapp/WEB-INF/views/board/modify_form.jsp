<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <script src="http://code.jquery.com/jquery-latest.min.js"></script>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/WEB-INF/views/default/header.jsp" %>
	<form action="modify" method="post" enctype="multipart/form-data">
		<input type="hidden" name="writeNo" value="${dto.writeNo }">
		<input type="hidden" name="originName" value="${dto.imageFileName }">
		제목 : <input type="text" name="title" value="${dto.title }"><br>
		내용 : <textarea rows="" cols="" name="content" value="${dto.content }"></textarea>
		<br>
		<img id="preview" src="download?fileName=${dto.imageFileName }" width="100"><br>
		<input type="file" name="imageFileName" onchange="readURL(this)"><br>
		<input type="submit" value="수정">
		<input type="button" onclick="history.back()" value="이전">
	</form>
	<script type="text/javascript"
		src="${path}/resources/js/readURL.js">
	</script>
</body>
<c:import url="../default/footer.jsp"/>
</html>



