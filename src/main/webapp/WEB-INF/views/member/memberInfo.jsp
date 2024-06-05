<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="../default/header.jsp"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<table border="1" style="width:900px;">
		<caption><font size="5">회원정보</font></caption>
			<tr><th>아이디</th><th>비밀번호</th><th>주소</th></tr>
			<c:forEach var="mem" items="${memberList }">
				<tr>
					<td>
						<a href="info?id=${mem.id }">${mem.id }</a>
					</td>
					<td>${mem.pw }</td><td>${mem.addr }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
<c:import url="../default/footer.jsp"/>
</html>