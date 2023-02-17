package com.green.nowon.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.domain.dto.GoodsDetailDTO;
import com.green.nowon.domain.dto.GoodsListDTO;
import com.green.nowon.domain.dto.goods.GoodsInsertDTO;
import com.green.nowon.domain.entity.CategoryGoodsEntity;
import com.green.nowon.domain.entity.CategoryGoodsEntityRepository;
import com.green.nowon.domain.entity.CategoryEntity;
import com.green.nowon.domain.entity.CategoryEntityRepository;
import com.green.nowon.domain.entity.GoodsEntity;
import com.green.nowon.domain.entity.GoodsEntityRepository;
import com.green.nowon.domain.entity.GoodsImgEntityRepository;
import com.green.nowon.service.GoodsService;
import com.green.nowon.utils.MyFileUtils;

@Service
public class GoodsServiceProcess implements GoodsService{
	
	
	@Value("${file.location.temp}")
	private String locationTemp;
	
	@Value("${file.location.upload}")
	private String locationUpload;
	
	@Autowired
	GoodsEntityRepository gRepository; //상품
	@Autowired
	CategoryGoodsEntityRepository cateItemRepo; //카테고리_상품 연계테이블
	@Autowired
	GoodsImgEntityRepository giRepository;//상품이미지
	@Autowired
	CategoryEntityRepository categoryRepository;//카테고리
	
	List<CategoryEntity> cates;
	
	@Transactional
	@Override
	public void save(GoodsInsertDTO dto) {

		//카테고리와 상품 등록
		//이미지 정보 등록, temp->실제 upload위치
		long[] categoryNo=dto.getCategoryNo();


		GoodsEntity entity=gRepository.save(dto.entity());
		for(long no:categoryNo) {
			cateItemRepo.save(CategoryGoodsEntity.builder()
					.goods(entity)
					.category(categoryRepository.findById(no).get())
					.build());
		}
		
		dto.toItemListImgs(entity, locationUpload).forEach(giRepository::save);
		//이미지 temp->temp->실제 upload위치
	}
			
	@Override
	public Map<String, String> fileTempUpload(MultipartFile gimg) {
		return MyFileUtils.fileUpload(gimg, locationTemp);
	}
	
	@Transactional
	@Override
	public void findAll(Model model) {
		List<GoodsListDTO> result=gRepository.findAll().stream()
				.map(GoodsListDTO::new).collect(Collectors.toList());
		
		model.addAttribute("list", result);
	}



	@Override
	public void categoryList(Model model) {
		model.addAttribute("list", 
				categoryRepository.findAllByParentCnoIsNull());
		
	}

	@Override
	public void categoryList(long cno, Model model) {
		model.addAttribute("list", 
				categoryRepository.findAllByParentCno(cno));		
		
	}


	@Override
	public void adminDetail(long gno, Model model) {
		GoodsDetailDTO result=gRepository.findById(gno)
				.map(GoodsDetailDTO::new)
				.orElseThrow();
		model.addAttribute("detail", result);
		
	}
	
	//상품디테일페이지
	@Transactional
	@Override
	public void detail(long gno, Model model) {
		model.addAttribute("detail", gRepository.findById(gno)
				.map(GoodsDetailDTO::new)
				.orElseThrow());
		
	}






	

}