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
	<div align="center"><h1>로그인 페이지 입니다</h1></div> 
	<div align="center">
		<form action = " /root/member/user_check" method="post">
			<table>
				<tr>
					<td>
						<input type="text" name="id" placeholder="아이디">
					</td> 
					<td rowspan="2">
						<input type="submit" value="로그인"style="width:60px;height:55px;"> 
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="pw" placeholder="비밀번호">
					</td>
				</tr> 
				<tr>
					<td colspan="2" align="left">
						<a href="register_form">회원가입</a>
					</td>
				</tr>
			</table>
			<input id="auto" type="checkbox" name="autoLogin">
			<label for="auto">자동 로그인</label>
		</form> 
	</div>
</body>
<c:import url="../default/footer.jsp"/>
</html>