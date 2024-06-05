package com.care.root.board.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface BoardFileService {
	public static final String IMAGE_REPO = "c:/spring/img";
	//public String getMessage(int num, HttpServletRequest req);
	public String saveFile(MultipartFile mul);
	public void deleteImage(String originName);
}
