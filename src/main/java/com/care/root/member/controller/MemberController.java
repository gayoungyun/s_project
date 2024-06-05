package com.care.root.member.controller;

import java.net.http.HttpRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.root.member.dto.MemberDTO;
import com.care.root.member.service.MemberService;
import com.care.root.member.session_name.MemberSessionName;
import com.care.root.mybatis.member.MemberMapper;

@Controller
@RequestMapping("member")//member라는 경로에대한 요청
public class MemberController implements MemberSessionName {
	@Autowired
	private MemberService ms;
	@GetMapping("login") 
	public String login(HttpSession session) {
		if(session.getAttribute(LOGIN) != null) {
			return "redirect:/index";
		}
		return "member/login";
	}
	@PostMapping("user_check")
	public String user_check(HttpServletRequest req, RedirectAttributes rs,
			@RequestParam(required = false) String autoLogin) {
		//RedirectAttributes : 리다이렉트할때 데이터를 전달하는 용도
		System.out.println("autoLogin : "+autoLogin);
		int result = ms.user_check(req);
		if(result == 0) {//성공적 인증
			rs.addAttribute("id", req.getParameter("id"));			
			rs.addAttribute("autoLogin",autoLogin);			
			return "redirect:successLogin";
		}
		return "redirect:login";
	}
	@RequestMapping("successLogin")
	public String successLogin(@RequestParam("id") String id, HttpSession session,
			@RequestParam(required = false) String autoLogin,
			HttpServletResponse res) {
		System.out.println("success login : "+autoLogin);
		if(autoLogin != null) {
			int limitTime = 60*60*24*90;// 24까지 하루, 90은 3개월 /90일동안 자동로그인 유지
			Cookie cook = new Cookie("loginCookie", id);
			cook.setPath("/");
			cook.setMaxAge(limitTime);
			res.addCookie(cook);
			
			ms.keepLogin( id, autoLogin );
		}
		//("id")는왜? : url에서 "id"라는 파라미터 값을 읽어와서 메서드 파라미터인 id변수에 할당, 앞뒤 값이 같으면 생략가능
		session.setAttribute(LOGIN, id);// =("loginUser", id)
		return "member/successLogin";
	}
	@GetMapping("logout")
	public String logout(HttpSession session, 
			@CookieValue(value="loginCookie", required = false) Cookie cook,
			HttpServletResponse res) {
		if(cook != null) {
			cook.setMaxAge(0);
			cook.setPath("/");
			res.addCookie(cook);
			ms.keepLogin((String)session.getAttribute(LOGIN), "nan");
		}
		if(session.getAttribute("loginUser") != null) {
			//(LOGIN)!=null?
			session.invalidate();
		}
		return "redirect:/index";
		//index는 기본 컨트롤러에 있어서 /꼭 붙여야함
	}
	@GetMapping("memberInfo")
	public String memberInfo(Model model, HttpSession session) {
		//model : 컨트롤러와 뷰간의 데이터를 전달하는데 사용되는 객체
		ms.memberInfo(model);
		return "member/memberInfo";
	}
	@RequestMapping("/info")//?/info
	public String info(Model model, @RequestParam("id") String userId) {
		
		ms.info(model,userId);
		return "member/info";
	}
	@RequestMapping("/register_form")
	public String register_form() {
		return "member/register";
	}
	@RequestMapping("/register")
	public String register(MemberDTO member, HttpServletRequest req) {//req 이용안해도 ,로 나눠서 나온다
		System.out.println("addr : "+member.getAddr());
		String [] addrs = req.getParameterValues("addr");//여러개받을때
		System.out.println(addrs[0]);
		System.out.println(addrs[1]);
		System.out.println(addrs[2]);
		
		int result = ms.register(member);
		if(result == 1) 
			return "redirect:login";
		return "redirect:register_form";
	}
}









