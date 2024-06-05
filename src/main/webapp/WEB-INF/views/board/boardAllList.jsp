<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="../default/header.jsp"/>
<c:set var="path" value="<%= request.getContextPath() %>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<table border="1">
		<caption><font size='6'>게시판</font></caption>
		<tr>
			<th>번호</th><th>id</th><th>제목</th><th>날짜</th>
			<th>조회수</th><th>image_file_name</th>
		</tr>
		<c:if test="${boardList.size() == 0 }">
		<tr>
			<th colspan='6'>등록된 글이 없습니다</th>
		</tr>
		</c:if>
		<c:forEach items="${boardList }" var="dto">
		<tr>
			<td>${dto.writeNo}</td><td>${dto.id}</td>
			<td>
				<a href="contentView?writeNo=${dto.writeNo}">${dto.title}</a>
			</td>
			<td>${dto.savedate}</td><td>${dto.hit}</td><td>${dto.imageFileName}</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan='6' align="right">
			<div align="center">
				<!-- <a href ="${path}/board/writeForm">글작성</a> 헤더에 path있어야하는데 해결안됨-->
				<c:forEach var="num" begin="1" end="${repeat }">
					<a href="boardAllList?start=${num }">${num }</a> &nbsp;
				</c:forEach>
			</div>
				<a href ="${path}/board/writeForm">글작성</a>
			</td>
		</tr>
	</table>
	</div>
</body>
<c:import url="../default/footer.jsp"/>
</html>







