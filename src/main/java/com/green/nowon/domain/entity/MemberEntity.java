package com.green.nowon.domain.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.green.nowon.security.MyRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SequenceGenerator(name = "seq_gen_member", 
		sequenceName = "seq_member", initialValue = 1, allocationSize = 1)
@Table(name = "member")
@Entity
public class MemberEntity extends BaseDateEntity{
	
	@GeneratedValue(generator = "seq_gen_member", strategy = GenerationType.SEQUENCE)
	@Id
	private long mno;
	
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String pass;
	@Column(unique = true)
	private String nickName;
	
	private boolean social;
	
	@Builder.Default
	@CollectionTable(name = "my_role")
	@Enumerated(EnumType.STRING)
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<MyRole> roles = new HashSet<>();
	
	public MemberEntity addRole(MyRole role) {
		roles.add(role);
		return this;
	}
	
}
