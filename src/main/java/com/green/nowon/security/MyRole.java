package com.green.nowon.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MyRole {
	
	USER("ROLE_USER"),
	ADMIN("ROLE_ADMIN");
	
	private final String role;
	
}
