package com.care.root.member.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.root.member.dto.MemberDTO;
import com.care.root.mybatis.member.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	MemberMapper mapper;
	BCryptPasswordEncoder en;
	public MemberServiceImpl() {
		en= new BCryptPasswordEncoder();
	}
	public int user_check(HttpServletRequest req) {
		MemberDTO dto = mapper.user_check(req.getParameter("id"));
		if(dto != null) {//해당아이디에 대한 회원정보가 조회되었음을 의미
			if(req.getParameter("pw").equals(dto.getPw()) || en.matches(req.getParameter("pw"), dto.getPw())) {
				return 0;//인증성공
			}
		}
		return 1;
	}
	public void memberInfo(Model model) {
		model.addAttribute("memberList", mapper.memberInfo());
	}
	public void info(Model model, String userId) {
		MemberDTO dto = mapper.info(userId);
		System.out.println(dto.getAddr());
		String[] a = dto.getAddr().split(",");
		model.addAttribute("info", dto);
	}
	private String replaceParameter(String addr) {
		addr = addr.replace("<", "&lt;");
		addr = addr.replace(">", "&gt;");
		addr = addr.replace("\"", "&quot;");
		return addr;
	}
	public int register(MemberDTO member) {
		//member.setAddr( replaceParameter(member.getAddr()));//위의 스크립트 기능안됨(주석처리시)
		System.out.println("변경 전 pwd : "+member.getPw());
		System.out.println("변경 후 pwd : "+en.encode(member.getPw()));
		member.setPw(en.encode(member.getPw()));
		
		try {
			return mapper.register(member);//등록결과 반환
		}catch (Exception e) {
			e.printStackTrace();
			return 0;//등록 실패 알림
		}
	}
	public void keepLogin( String id, String autoLogin ) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("autoLogin", autoLogin);
		mapper.keepLogin(map);
	}
}








