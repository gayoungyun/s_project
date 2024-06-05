<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <c:import url="../default/header.jsp"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:set var="contextPath" value="${ pageContext.request.contextPath}"/>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/resources/js/readURL.js">
</script>
</head>
<body>
<div id="wrap" style="width: 400px; margin: 0 auto; ">
	<h1 style="text-align: center">글쓰기</h1>
	<form action="writeSave" method="post" enctype="multipart/form-data">
		<b>작성자</b><br>
		<input type="text" name="id" value="${loginUser }" readonly/>
		<hr>
		<b>제목</b> <br><input type="text" size="50" name="title"/><hr>
		<b>내용</b> <br>
		<textarea rows="10" cols="50" name="content"></textarea><hr>
		<b>이미지파일 첨부</b><br>
		<input type="file" name="image_file_name" onchange="readURL(this);"/>
		<img id="preview" src="" width="100" height="100" alt="선택된 이미지가 없습니다"/>
		<hr>
		<input type="submit" value="글쓰기"/>	
		<input type=button value="목록보기"
			onClick="location.href='${contextPath}/board/boardAllList'"><!-- a태그가능 -->
	</form>
</body>
<c:import url="../default/footer.jsp"/>
</html>










