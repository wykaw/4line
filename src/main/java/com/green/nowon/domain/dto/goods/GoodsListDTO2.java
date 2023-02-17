package com.green.nowon.domain.dto.goods;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import com.green.nowon.domain.entity.CategoryGoodsEntity;
import com.green.nowon.domain.entity.GoodsEntity;
import com.green.nowon.utils.MyFileUtils;

import lombok.Data;

@Data
public class GoodsListDTO2 {

	private long gno;
	private String title;
	private int price;
	private int stock;
	
	
	private int sPrice;
	private int dPrice;
	//이미지 대표이미지
	private String defImgUrl;

	public GoodsListDTO2(GoodsEntity e) {
		this.gno = e.getGno();
		this.title = e.getTitle();
		this.price = e.getPrice();
		this.stock = e.getStock();
		this.defImgUrl = e.defImg().getUrl()+e.defImg().getNewName();
		sPrice=0;
		dPrice=3000;
	}
	public GoodsListDTO2(CategoryGoodsEntity cie) {
		this(cie.getGoods());
	}
	
	
}
