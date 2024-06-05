package com.care.root.mybatis.member;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.care.root.member.dto.MemberDTO;

public interface MemberMapper {
	public MemberDTO user_check(String id);
	public ArrayList<MemberDTO> memberInfo();
	public MemberDTO info(String userId);
	public int register(MemberDTO member);
	public void keepLogin( Map<String, Object> map );
}
