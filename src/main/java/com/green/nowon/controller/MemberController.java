package com.green.nowon.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.nowon.domain.dto.member.MemberInsertDTO;
import com.green.nowon.domain.dto.member.MemberCheckDTO;
import com.green.nowon.domain.dto.member.MemberIdCheck;
import com.green.nowon.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
	
	@Autowired
	private MemberService mService;
	
	@GetMapping("/members/signin")
	public String signin() {return "views/sign/signin";}
	
	@GetMapping("/members/signup")
	public String signup() {return "views/sign/signup";}
	
	@ResponseBody
	@GetMapping("/check/idCheck/{email}")
	public boolean check(@PathVariable String email) {
		return mService.idCheck(email);
	}	
	
	@ResponseBody//ajax
	@GetMapping("/check/nickNameCheck/{nickName}")
	public boolean nickCheck(@PathVariable String nickName) {
		return mService.nickNameCheck(nickName);
	}
	
	/**
	 * 회원가입 
	 * @param dto 데이터 삽입 dto
	 * @return 회원가입시 로그인창으로 이동
	 */
	
	@PostMapping("/members/join")
	public String join(MemberInsertDTO dto) {
		mService.save(dto);
		return "redirect:/members/signin";
	}
	
	@ResponseBody
	@GetMapping("/member/signin-check")
	public boolean loginCheck(Authentication auth) {
		//로그인 시는 인증정보 확인 가능
		//비로그인 시는 null
		return auth==null ? false : true;
	}
	
}
