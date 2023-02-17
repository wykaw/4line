package com.green.nowon.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.domain.dto.goods.GoodsInsertDTO;
import com.green.nowon.service.GoodsService;

@Controller
public class GoodsController {
	
	@Autowired
	GoodsService gService;
	
	//상품디테일페이지
	@GetMapping("/common/goods/{gno}")
	public String detail(@PathVariable long gno, Model model) {
		gService.detail(gno, model);
		return "/goods/detail";
	}
	
	@GetMapping("/admin/goods/reg")
	public String goods() {
		return "goods/reg";
	}
	
	@PostMapping("/admin/goods/reg")
	public String goodsInsert(GoodsInsertDTO dto) {
		gService.save(dto);
		return "redirect:/admin/goods/list";
	}

	@ResponseBody//응답데이터를 json타입으로 리턴합니다. 
	@PostMapping("/admin/temp-upload")
	public Map<String,String> tempUpload(MultipartFile gimg) {
		return gService.fileTempUpload(gimg);
	}
	
	
	@GetMapping("/comm/goods/list")
	public String userGoodsList(Model model) {
		gService.findAll(model);
		return "goods/user-list";
	}

	//1차 카테고리 불러오기
	@GetMapping("/common/categorys")
	public String categoryList(Model model) {
		gService.categoryList(model);
		return "goods/category";
	}
	
	//서브(2차) 카테고리
	@GetMapping("/common/categorys/{cno}")
	public String categoryList(@PathVariable long cno, Model model) {
		gService.categoryList(cno, model);
		return "goods/category";
	}
	
	
}