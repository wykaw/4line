package com.green.nowon;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.green.nowon.domain.dto.member.MemberInsertDTO;
import com.green.nowon.domain.entity.MemberEntity;
import com.green.nowon.domain.entity.MemberEntityRepository;
import com.green.nowon.security.MyRole;

@SpringBootTest
class MyShopApplicationTests {

	@Autowired
	private MemberEntityRepository mrepo;
	
	@Autowired
	private PasswordEncoder pe;
	
	@Test
	void contextLoads() {
		mrepo.save(MemberEntity.builder()
					.mno(1)
					.email("admin@admin.com")
					.pass(pe.encode("1234"))
					.nickName("admin")
					.build()
				.addRole(MyRole.USER)
				.addRole(MyRole.ADMIN)
				);
		
	}

}
