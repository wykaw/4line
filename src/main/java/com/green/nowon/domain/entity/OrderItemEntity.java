package com.green.nowon.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "order_item")
@Entity
public class OrderItemEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long oino;
	
	private int orderPrice;//주문금액
	private int quantity;//주문수량
	
	@JoinColumn//ono
	@ManyToOne
	private OrderEntity order;
	
	@JoinColumn//gno
	@ManyToOne
	private GoodsEntity goods;
	
}
