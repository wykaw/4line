package com.green.nowon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.nowon.domain.dto.cart.CartGoodsSaveDTO;
import com.green.nowon.security.MyUserDetails;
import com.green.nowon.service.CartService;

@Controller
public class CartController {
	
	@Autowired
	private CartService service;
	
	//인증이후 처리되는 url
	@ResponseBody
	@PostMapping("/member/cart")
	public void cartGoods(CartGoodsSaveDTO dto,@AuthenticationPrincipal MyUserDetails myUserDetails) {
		
		
		//System.out.println("이메일:"+myUserDetails.getEmail());
		service.save(dto, myUserDetails.getEmail());
	}
	
	//장바구니 페이지
	@GetMapping("/member/cart")
	public String cart(Model model, @AuthenticationPrincipal MyUserDetails myUserDetails) {
		
		service.cart(model, myUserDetails.getEmail());
		return "goods/cart";
	}


}