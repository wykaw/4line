package com.green.nowon.domain.dto.goods;


import com.green.nowon.domain.entity.GoodsEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
public class OrderItemListDTO {
	private GoodsListDTO2 item;
	
	private int quantity;
	
	//수량에따른 가격
	private int totPrice;
	
	public OrderItemListDTO quantity(int quantity) {
		this.quantity=quantity;
		this.totPrice=quantity*(item.getPrice()-item.getSPrice());
		return this;
	}
	//주문금액
	public OrderItemListDTO(GoodsEntity e){
		this.item=new GoodsListDTO2(e);
	}
}
