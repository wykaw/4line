package com.green.nowon.domain.dto.member;


import java.time.LocalDateTime;

import com.green.nowon.domain.entity.MemberEntity;

import lombok.Getter;


@Getter
public class MemberListInsertDTO { 	//admin회원리스트DTO
	private long mno;
	private String email;
	private String nickName;
	private LocalDateTime updatedDate;
	
	public MemberListInsertDTO(MemberEntity e) {
		this.mno = e.getMno();
		this.email = e.getEmail();
		this.nickName = e.getNickName();
		this.updatedDate = e.getUpdatedDate();
	}
}
