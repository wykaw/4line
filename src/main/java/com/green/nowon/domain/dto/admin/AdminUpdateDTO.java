package com.green.nowon.domain.dto.admin;

import java.util.ArrayList;
import java.util.List;

import com.green.nowon.domain.entity.GoodsEntity;
import com.green.nowon.domain.entity.GoodsImgEntity;
import com.green.nowon.utils.MyFileUtils;

import lombok.Data;

@Data
public class AdminUpdateDTO {
	
	private String title;
	private String content;
	private int price;
	private int stock;
	
	private String[] newName;
	private String[] orgName;

	public List<GoodsImgEntity> toItemListImgs(GoodsEntity entity, String url) {
		List<GoodsImgEntity> imgs = new ArrayList<>();
		for (int i = 0; i < orgName.length; i++) {
			if(orgName[i].equals("") || orgName[i]==null)continue;
			boolean def = false;
			if(i==0)def=true;
			GoodsImgEntity gim = GoodsImgEntity.builder()
					.url(url)
					.orgName(orgName[i])
					.newName(newName[i])
					.def(def)
					.goods(entity)
					.build();
			imgs.add(gim);
		}
		MyFileUtils.moveUploadLocationFromTemp(newName, url);
		return imgs;
	}
	
	public GoodsEntity updateEntity() {
		return GoodsEntity.builder()
				.title(title)
				.content(content)
				.price(price)
				.stock(stock)
			.build();
	}
	
}
