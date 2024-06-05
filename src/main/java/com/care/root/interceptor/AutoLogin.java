package com.care.root.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.care.root.member.dto.MemberDTO;
import com.care.root.member.service.MemberService;
import com.care.root.member.session_name.MemberSessionName;
import com.care.root.mybatis.member.MemberMapper;

public class AutoLogin extends HandlerInterceptorAdapter
											implements MemberSessionName{
	@Autowired MemberMapper mapper;
	@Override
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("auto login 실행");
		Cookie cook =  WebUtils.getCookie(request, "loginCookie");//값은 쿠키이름, webutils로 쿠키값얻어옴
		System.out.println("cook : "+cook);
		if(cook != null) {
			MemberDTO dto = mapper.info(cook.getValue());
			if(dto.getSessionId().equals("on")) {
				request.getSession().setAttribute(LOGIN, dto.getId());
			}
		}
		return true;
	}
	

}
