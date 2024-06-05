package com.care.root.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.care.root.board.dto.BoardRepDTO;
import com.care.root.board.service.BoardService;
import com.care.root.member.session_name.MemberSessionName;

@RestController
@RequestMapping("board")
public class BoardRepController {
	@Autowired BoardService bs;
	@PostMapping(value="reply", produces = "application/json; charset=utf-8")
	public void addRep(@RequestBody Map<String, String> map,
					HttpSession session) {
		System.out.println(map.get("write_no"));
		System.out.println(map.get("title"));
		System.out.println(map.get("content"));
		bs.addRep(map, (String)session.getAttribute(MemberSessionName.LOGIN));
	}
	@GetMapping(value="reply/{wg}", produces = "application/json; charset=utf-8")
	public List<BoardRepDTO> getData(@PathVariable int wg) {
		System.out.println("wg : "+wg);
		return bs.getData(wg);
	}
}









