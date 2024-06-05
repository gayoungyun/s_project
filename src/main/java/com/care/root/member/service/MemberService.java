package com.care.root.member.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.care.root.member.dto.MemberDTO;

public interface MemberService {
	public int user_check(HttpServletRequest req);
	public void memberInfo(Model model);
	public void info(Model model, String userId);
	public int register(MemberDTO member);
	public void keepLogin( String id, String autoLogin );
}
