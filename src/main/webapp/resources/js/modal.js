function slideClick(writeNo){
	$("#first").slideDown("slow");
	$("#modal_wrap").show()
	$("#write_no").val(writeNo);
}
function slide_hide(){
	$("#title").val("");
	$("#content").val("");
	$("#first").hide();
	$("#modal_wrap").hide();
	
}
function rep(){
	let form = {};
	let arr = $("#frm").serializeArray();
	console.log(arr);
	for(let i = 0; i<arr.length ; i++){
		form[arr[i].name] = arr[i].value;
	}
	console.log(form);
	$.ajax({
		url : "reply", type : "post",
		data : JSON.stringify(form),
		contentType : "application/json; charset=utf-8",
		success : function(){
			alert("답글이 달렸습니다");
			slide_hide();
			$("#data").before("새로운 내용 추가")
			readData(form.write_no);
		},error : function(){
			alert("문제 발생");
		}
	});
}
function readData(write_group){
	$.ajax({
		url : "reply/"+write_group, type : "get",
		dataType : "json",
		success : function(data){
			console.log("data", data);
			
			let html = "";
			data.forEach(function(d){
				let date = new Date(d.write_date);
				let strDate = date.getFullYear()+"년";
				strDate += (date.getMonth()+1)+"월";
				strDate += date.getDate()+"일";
				strDate += date.getHours()+"시";
				strDate += date.getMinutes()+"분";
				strDate += date.getSeconds()+"초";
				
				html += "아이디 : "+d.id+"님 /";
				html += "작성일 : "+strDate+"<br>";
				html += "제목 : "+d.title+"<br>";
				html += "내용 : "+d.content+"<hr>";
			})
			console.log("html : ",html);
			$("#data").html(html)
		}
	});
}





