package com.green.nowon.domain.dto.cart;

import com.green.nowon.domain.dto.GoodsListDTO;
import com.green.nowon.domain.entity.CartGoodsEntity;

import lombok.Getter;

@Getter
public class CartGoodsListDTO {
	
	private long no; //cartGoods no
	private int quantity; //구매수량
	
	private int dPrice; //배송비
	
	private GoodsListDTO goods;

	public CartGoodsListDTO(CartGoodsEntity e) {
		this.no = e.getNo();
		this.quantity = e.getQuantity();
		this.goods = new GoodsListDTO(e.getGoods());
		this.dPrice = 3000;
	}

}