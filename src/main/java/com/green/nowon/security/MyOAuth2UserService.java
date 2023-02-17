package com.green.nowon.security;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.green.nowon.domain.entity.MemberEntity;
import com.green.nowon.domain.entity.MemberEntityRepository;
							//class DefaultOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>
public class MyOAuth2UserService extends DefaultOAuth2UserService {

	@Autowired
    MemberEntityRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;
    
    
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		OAuth2User oAuth2User =super.loadUser(userRequest);
		
		Map<String, Object> attributes= oAuth2User.getAttributes();
		
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		
		MyUserDetails myUserDetails= setMyUserDetails(attributes,oAuth2User,registrationId);

		/////////////////////////////////////////////////강사님 소스
		
        MemberEntity memberEntity=null;//아이디찾기
        Optional<MemberEntity> result = repository.findByEmailAndSocial(myUserDetails.getEmail(),myUserDetails.isSocial());
        
        //1.같은 이메일 다른 소셜아이디 허용의 건에 대하여
        
        //2. 소셜로그인으로 하는경우 db에 유저 정보가 저장되는데 nickName이 같은경우
        
        if(result.isPresent()){
            memberEntity=result.get();
        }else{  //존재하지 않으면 새로 생성
            memberEntity = MemberEntity.builder()
                    .email(myUserDetails.getEmail())
                    .nickName(myUserDetails.getNickName())
                    .pass(passwordEncoder.encode("1111"))
                    .social(myUserDetails.isSocial())
                    .build().addRole(MyRole.USER);
            repository.save(memberEntity);
        }

        myUserDetails.setMno(memberEntity.getMno());
        
        
        return myUserDetails;
	}
	
	private MyUserDetails setMyUserDetails(Map<String, Object> attributes, OAuth2User oAuth2User, String registrationId) {

        Set<SimpleGrantedAuthority> authorities=null;
        //private String nameAttributeKey;
        String nickName=null;
        String email=null;
        //private boolean social;
        String profileImg=null;

        for(String key : attributes.keySet()){
            System.out.println(">>>> "+key+" : "+attributes.get(key));//key,value
        }

        authorities = (Set<SimpleGrantedAuthority>) oAuth2User.getAuthorities();
        
        if (registrationId.equals("google")) {//구골
            attributes = oAuth2User.getAttributes();
            email = (String) attributes.get("email");
            nickName = (String) attributes.get("name");
        } else if (registrationId.equals("naver")) {//네이
            attributes = oAuth2User.getAttribute("response");    
            email = (String) attributes.get("email");
            nickName = (String) attributes.get("name");
        } else if (registrationId.equals("kakao")) {//카카
            attributes = oAuth2User.getAttribute("kakao_account");
            email = (String) attributes.get("email");
            nickName = ((Map<String, String>) attributes.get("profile")).get("nickname");
        }

        return new MyUserDetails(email, passwordEncoder.encode("11"), authorities ,nickName);
    }
}