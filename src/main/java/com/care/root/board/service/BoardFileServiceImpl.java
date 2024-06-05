package com.care.root.board.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BoardFileServiceImpl implements BoardFileService{
	/*
	public String getMessage(int num, HttpServletRequest req) {
		String message = null;
		String path = req.getContextPath();
		//message = "<script>alert('"+msg+"');";
		//message += "location.href='"+path+url+"';</script>";
		return message;
	}
	*/
	public String saveFile(MultipartFile mul) {
		SimpleDateFormat simpl = new SimpleDateFormat("yyyyMMddHHmmss-");
		//Calendar calendar = Calendar.getInstance();
		String sysFileName = simpl.format(new Date()) +mul.getOriginalFilename();
		File saveFile = new File(IMAGE_REPO+"/"+sysFileName);
		try {
			mul.transferTo(saveFile); //해당 위치에 파일 저장 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysFileName;
	}
	public void deleteImage(String originName) {
		File file = new File(IMAGE_REPO+"/"+originName);
		if(file.exists())
			file.delete();
	}

}





