package com.green.nowon.domain.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.green.nowon.domain.dto.admin.GoodsImgDTO;
import com.green.nowon.domain.entity.GoodsEntity;

import lombok.Data;


@Data
public class GoodsDetailDTO {
	
	private long gno;
	private String title;
	private int price;
	private int stock;
	private String content;
	private LocalDateTime updatedDate;
	private LocalDateTime createdDate;
	
	//대표이미지 사용을 위해 미리 출력
	private String defImgUrl;
	
	private String orgImgUrl;
	
	private List<GoodsImgDTO> imgs;
	
	public GoodsDetailDTO(GoodsEntity e) {
		this.gno =e.getGno();
		this.title = e.getTitle();
		this.content = e.getContent();
		this.price = e.getPrice();
		this.stock = e.getStock();
		this.createdDate=e.getCreatedDate();
		this.updatedDate=e.getUpdatedDate();
		
		imgs=e.getImgs().stream()
				.map(GoodsImgDTO::new)
				.collect(Collectors.toList());
		
		this.defImgUrl = e.defImg().getUrl()+e.defImg().getNewName();
		this.orgImgUrl = e.defImg().getUrl() + e.defImg().getOrgName();
	}
	
	
		

}
