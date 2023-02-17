package com.green.nowon.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.green.nowon.domain.dto.member.MemberCheckDTO;
import com.green.nowon.domain.dto.member.MemberIdCheck;
import com.green.nowon.domain.dto.member.MemberInsertDTO;
import com.green.nowon.domain.entity.MemberEntity;
import com.green.nowon.domain.entity.MemberEntityRepository;
import com.green.nowon.security.MyRole;
import com.green.nowon.service.MemberService;

@Service
public class MemberServiceProcess implements MemberService {
	
	//DB에 접속하여 MemberEntity에 접근하는 DAO객체 필요
	@Autowired
	private MemberEntityRepository mrepo;
	
	@Autowired
	private PasswordEncoder pe;
	
	@Override
	public void save(MemberInsertDTO dto) {
		mrepo.save(dto.entity(pe).addRole(MyRole.USER));
	}

	@Override
	public boolean idCheck(String email) {
		return mrepo.existsByEmail(email);
	}
	
	@Override
	public boolean nickNameCheck(String nickName) {
		return mrepo.existsByNickName(nickName);
	}

}
