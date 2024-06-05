<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<div id="modal_wrap">
		<div id="first">
			<div style="width: 250px; margin: auto; padding-top: 20px;">
				<form id="frm">
					<input type="hidden" id="write_no" name="write_no"> 답글 작성
					페이지
					<hr>
					작성자 : ${loginUser }<br> 제목 : <input type="text" name="title"
						id="title"><br> 내용 :
					<textarea name="content" id="content"></textarea>
					<hr>
					<button type="button" onclick="rep()">답글</button>
					<button type="button" onclick="slide_hide()">취소</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>