package com.green.nowon.service;

import com.green.nowon.domain.dto.member.MemberInsertDTO;
import com.green.nowon.domain.dto.member.MemberCheckDTO;
import com.green.nowon.domain.dto.member.MemberIdCheck;

public interface MemberService {
	void save(MemberInsertDTO dto);

	boolean idCheck(String email);
	boolean nickNameCheck(String nickName);
}
