package com.care.root.board.dto;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class BoardDTO {
	private int writeNo;
	private String title;
	private String content;
	private String savedate; //날짜 설정
	private int hit;
	private String  imageFileName;
	private String id;
	
	public int getWriteNo() {
		return writeNo;
	}
	public void setWriteNo(int writeNo) {
		this.writeNo = writeNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getsavedate() {
		return savedate;
	}
	/*
	public void setSavadate(String savadate) {
		this.savadate = savadate;
	}
	*/
	public void setSavedate(Timestamp time) {
		SimpleDateFormat f= new SimpleDateFormat("YYYY년 MM월 dd일 HH시 mm분 ss초");
		this.savedate = f.format(time);
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}