package com.care.root.board.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.dto.BoardRepDTO;
import com.care.root.member.session_name.MemberSessionName;
import com.care.root.mybatis.board.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired 
	BoardMapper mapper;
	@Autowired
	BoardFileService bfs;
	public void boardAllList(Model model,int start) {
		int pageLetter = 3;
		int allCount = mapper.getCounter();//글 총개수
		int repeat = allCount / pageLetter; //나눠서 몫만큼 페이지수
		if(allCount % pageLetter != 0) {
			repeat += 1;//페이지수
		}
		int e = start * pageLetter;
		int s = e + 1 - pageLetter;
		model.addAttribute("repeat", repeat);//반복문동작위해
		model.addAttribute("boardList", mapper.boardAllList(s, e));//키 list, board~부터 db얻어오겠다
	}
	public int writeForm(BoardDTO board) {
		//board.setId((board.getId()));
		try {
			return mapper.writeSave(board);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	public String writeSave(MultipartFile mul,String id, String content, String title) {
		BoardDTO dto = new BoardDTO();
		dto.setTitle(title);
		dto.setId(id);
		dto.setContent(content);
		dto.setImageFileName("nan");
		
		if(!mul.isEmpty()) {
			//이미지 저장 및 이름 변경
			System.out.println("dto : "+dto);
			System.out.println("bfs : "+bfs);
			System.out.println("mul : "+mul);
			dto.setImageFileName(bfs.saveFile(mul));//이미지 있을경우 처리 
		}
		int result = 0;
		try {
			result = mapper.writeSave(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String msg, url;
		if(result == 1) {
			msg = "새글이 추가되었습니다";
			url = "/board/boardAllList";
		}else {
			msg = "문제가 발생되었습니다!";
			url = "/board/writeForm";
		}
		return getMessage(msg, url);
	}
	private String getMessage(String msg, String url) {
		String message= "<script>alert('"+msg+"');";
		message += "location.href='/root"+url+"';</script>";//root앞에 /없으면 상대경로라서 경로를 잘못인식
		return message;
	}
	private void upHit(int writeNo) {
		mapper.upHit(writeNo);
	}
	public void contentView(int writeNo, Model model) {
		upHit(writeNo);
		model.addAttribute("dto", mapper.contentView(writeNo));
	}
	public String modify(MultipartHttpServletRequest mul) {
		BoardDTO dto = new BoardDTO();
		dto.setWriteNo(Integer.parseInt(mul.getParameter("writeNo")));
		dto.setTitle(mul.getParameter("title"));
		dto.setContent(mul.getParameter("content"));
		
		MultipartFile m= mul.getFile("imageFileName");
		if(m.isEmpty()) {
			dto.setImageFileName(mul.getParameter("originName"));
		}else {
			dto.setImageFileName(bfs.saveFile(m));
			bfs.deleteImage(mul.getParameter("originName"));
		}
		int result = mapper.modify(dto);
		String msg, url;
		if(result == 1) {
			msg = "수정성공!!";
			url = //mul.getContextPath() + 
					"/board/contentView?writeNo="+dto.getWriteNo();
		}else {
			msg = "문제발생!!";
			url = //mul.getContextPath() + 
					"/board/modify_form?writeNo="+dto.getWriteNo();
		}
		return getMessage(msg, url);
	}
	public String delete(int writeNo, String fileName) {
		int result = mapper.delete(writeNo);
		String msg, url;
		if(result == 1) {
			msg = "삭제 성공!!";
			url = "/board/boardAllList";
			bfs.deleteImage(fileName);
		}else {
			msg = "문제 발생!!";
			url = "/board/contentView?writeNo="+writeNo;
		}
		return getMessage(msg, url);
	}
	public void addRep(Map<String, String>map, String userId) {
		map.put("userId", userId);
		mapper.addRep(map);
	}
	public List<BoardRepDTO> getData( int wg){
		return mapper.getData(wg);
	}
}








