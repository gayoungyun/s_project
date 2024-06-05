package com.care.root.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.care.root.member.session_name.MemberSessionName;

public class MemberInterceptor extends HandlerInterceptorAdapter
							implements MemberSessionName{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("index 실행 전 출력");
		HttpSession session = request.getSession();
		
		if(session.getAttribute(LOGIN) == null ) {
			//response.sendRedirect("login");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>alert('로그인 먼저하세요');"
						+"location.href='/root/member/login';</script>");//상대경로시 스크립트이후 에러가뜬다
			return false;
		}
		return true;//로그인이 되어있다면 true를 반환해서 요청 처리를 계속한다
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("index 실행 후 동작");
	} 
	
}
