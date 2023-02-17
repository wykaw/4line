package com.green.nowon.service.impl;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.cart.CartGoodsListDTO;
import com.green.nowon.domain.dto.cart.CartGoodsSaveDTO;
import com.green.nowon.domain.entity.CartEntity;
import com.green.nowon.domain.entity.CartEntityRepository;
import com.green.nowon.domain.entity.CartGoodsEntity;
import com.green.nowon.domain.entity.CartGoodsEntityRepository;
import com.green.nowon.domain.entity.GoodsEntityRepository;
import com.green.nowon.domain.entity.MemberEntityRepository;
import com.green.nowon.service.CartService;

@Service
public class CartServiceProcess implements CartService{
	
	@Autowired
	private CartEntityRepository cartRepo;
	
	@Autowired
	private MemberEntityRepository memberRepo;
	
	@Autowired
	private CartGoodsEntityRepository cartGoodsRepo;
	
	@Autowired
	private GoodsEntityRepository goodsRepo;
	
	//장바구니 페이지

		@Transactional
		@Override
		public void cart(Model model, String email) {
			
			model.addAttribute("list", cartGoodsRepo.findAllByCartMemberEmail(email).stream()
								.map(CartGoodsListDTO::new)
								.collect(Collectors.toList()));
			
		}
	
	@Override
	public void save(CartGoodsSaveDTO dto, String email) {
		
		//처음 저장 시 카트는 존재하지 않음
		//MemberEntity member=memRepository.findByEmail(email).orElseThrow();
		CartEntity cart=cartRepo.findByMemberEmail(email)
				.orElseGet(()->cartRepo.save(CartEntity.builder()
						.member(memberRepo.findByEmail(email).orElseThrow())
						.build()));
		
		System.out.println(">>>>>>>카트가 존재하지 않으면 카트 생성됨");
		
		//만약 카트에 동일아이템이 이미 존재하는 경우
		// if문 사용X
		cartGoodsRepo.findByCartNoAndGoodsGno(cart.getNo(), dto.getItemNo())
			.ifPresentOrElse(
					//존재할 때는 구매수량만 증가시키기(업데이트)
					e->e.updateQuantity(dto.getQuantity()), 
					//존재하지 않을 때는 저장
					()->cartGoodsRepo.save(CartGoodsEntity.builder()
							.cart(cart)
							.goods(goodsRepo.findById(dto.getItemNo()).get())
							.quantity(dto.getQuantity())
							.build()) 
			);
	
	}

}
