package com.green.nowon.domain.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.green.nowon.domain.dto.admin.GoodsImgDTO;
import com.green.nowon.domain.entity.GoodsEntity;

import lombok.Data;
import lombok.Getter;

@Data
public class GoodsListDTO {
	
	private long gno;
	private String title;
	private int price;
	private int stock;
	private LocalDateTime updatedDate;
	
	//대표이미지 사용을 위해 미리 출력
	private String defImgUrl;
	//쪼꼬미 이미지 출력을 위해 
	private List<GoodsImgDTO> imgs;
	
	public GoodsListDTO(GoodsEntity e) {
		this.gno =e.getGno();
		this.title = e.getTitle();
		this.price = e.getPrice();
		this.stock = e.getStock();
		this.updatedDate=e.getUpdatedDate();
		
		imgs=e.getImgs().stream()
				.map(GoodsImgDTO::new)
				.collect(Collectors.toList());
		System.out.println("1 : "+defImgUrl);
		this.defImgUrl = e.defImg().getUrl()+e.defImg().getNewName();
		System.out.println("2 : "+defImgUrl);
	}
	
}