package com.green.nowon.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "category_goods")
@Entity
public class CategoryGoodsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cgno;
	
	@JoinColumn//category_cno
	@ManyToOne
	private CategoryEntity category;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn//goods_gno
	@ManyToOne
	private GoodsEntity goods;



}