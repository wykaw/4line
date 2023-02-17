package com.green.nowon.domain.dto.admin;

import com.green.nowon.domain.entity.GoodsImgEntity;

import lombok.Getter;

@Getter
public class GoodsImgDTO {
	
	private long fno;
	private String orgName;
	private String newName;
	private String url;
	private boolean defImg;
	//편의필드
	private String imgUrl;
	
	private String orgImgUrl;
	
	public GoodsImgDTO(GoodsImgEntity e) {
		this.fno = e.getFno();
		this.orgName = e.getOrgName();
		this.newName = e.getNewName();
		this.url = e.getUrl();
		this.defImg = e.isDef();
		
		this.imgUrl = url+newName;
		this.orgImgUrl = url+orgName;
	}


}
