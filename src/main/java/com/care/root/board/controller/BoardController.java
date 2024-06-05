package com.care.root.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.service.BoardFileService;
import com.care.root.board.service.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {
	@Autowired BoardService bs;
	@GetMapping("boardAllList")
	public String boardAllList(Model model,
				@RequestParam(value="start", required= false, defaultValue = "1") int start) {
		
		bs.boardAllList(model, start);
		return "board/boardAllList";
	}
	@GetMapping("writeForm")
	public String writeForm() {
		return "board/writeForm";
	}
	/*
	@RequestMapping("writeForm")
	public String writeForm(BoardDTO dto){
		System.out.println("id : "+dto.getId());
		int result = bs.writeForm(dto);
		if(result ==1)
			return "redirect:login";
		
		return "redirect:write_form";
	}
	*/
	@PostMapping("writeSave")
	public void writeSave(@RequestParam("image_file_name") MultipartFile mul, 
						//그냥 mul만하면 null값도 넘어가서 jsp에서 변수이름과 맞춰야한다
						HttpServletResponse res,
						@RequestParam String id, 
						@RequestParam String content,
						@RequestParam String title) throws IOException{
		String msg = bs.writeSave(mul,id, content, title);
		PrintWriter out= res.getWriter();
		res.setContentType("text/html; charset=utf-8");
		out.print(msg);
	}
	@GetMapping("contentView")
	public String contentView(@RequestParam int writeNo, Model model) {//("writeNo")
		bs.contentView(writeNo,model);//,0
		return "board/contentView";
	}
	@GetMapping("download")
	public void download(@RequestParam String fileName,
						HttpServletResponse res) throws Exception{
		res.addHeader("Content-disposition", "attachment;fileName="+fileName);
		File file = new File(BoardFileService.IMAGE_REPO+"/"+fileName);
		if( file.exists()) {// 에러메시지 안보이기 위한
			FileInputStream in = new FileInputStream(file);
			FileCopyUtils.copy(in, res.getOutputStream());
			in.close();
		}
	}
	@GetMapping("modify_form")
	public String modifyForm(Model model,
							@RequestParam int writeNo) {
		bs.contentView(writeNo, model);//,1
		//나중에는 getContent를 따로 만들어야 조회수가 같이 안올라감, 안만드려면 번호를 하나 넘겨줌
		return "board/modify_form";
	}
	@PostMapping("modify")
	public void modify(MultipartHttpServletRequest mul,
					HttpServletResponse res) throws Exception{
		String msg = bs.modify(mul);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(msg);
	}
	@GetMapping("delete")
	public void delete(@RequestParam int writeNo,
						@RequestParam String fileName,
						HttpServletResponse res) throws IOException{
		String msg = bs.delete(writeNo, fileName);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(msg);
		
	}
	
}









