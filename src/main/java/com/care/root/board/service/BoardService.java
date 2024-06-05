package com.care.root.board.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.dto.BoardRepDTO;

public interface BoardService {
	public void boardAllList(Model model, int start);
	//public int writeForm(BoardDTO board);
	public String writeSave(MultipartFile mul,String id, String content, String title);
	public void contentView( int writeNo, Model model);
	public String modify(MultipartHttpServletRequest mul);
	public String delete(int writeNo, String fileName);
	public void addRep(Map<String, String>map, String userId);
	public List<BoardRepDTO> getData( int wg);
}
