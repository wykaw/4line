package com.green.nowon.security;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.green.nowon.domain.entity.MemberEntity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class MyUserDetails extends User implements OAuth2User {
	
	private long mno;
	private String email;
	private String name;
	private String nickName;
	private boolean social;
	
	private Map<String, Object> attributes;
	
	public MyUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public MyUserDetails(MemberEntity entity) {
		this(entity.getEmail(), entity.getPass(), entity.getRoles() //Set<MyRole> ---> Set<GrantedAuthority>
				.stream() //Stream<MyRole>
				.map(myRole -> new SimpleGrantedAuthority(myRole.getRole())) //Stream<GrantedAuthority> "ROLE_USER" or "ROLE_ADMIN"
				.collect(Collectors.toSet()));
		this.mno=entity.getMno();
		this.email=entity.getEmail();
		this.nickName=entity.getNickName();
        this.social=entity.isSocial();
	}
	
	public MyUserDetails(String email, String pass, Set<SimpleGrantedAuthority> authorities, String nickName) {
        this(email, pass, authorities);
        this.email=email;
        this.social=true;
        this.nickName=nickName;
    }
	
	@Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }
}