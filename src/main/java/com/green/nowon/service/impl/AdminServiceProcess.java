package com.green.nowon.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.admin.AdminUpdateDTO;
import com.green.nowon.domain.dto.member.MemberListInsertDTO;
import com.green.nowon.domain.entity.GoodsEntity;
import com.green.nowon.domain.entity.GoodsEntityRepository;
import com.green.nowon.domain.entity.GoodsImgEntityRepository;
import com.green.nowon.domain.entity.MemberEntityRepository;
import com.green.nowon.service.AdminService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminServiceProcess implements AdminService {

	@Value("${file.location.temp}")
	private String locationTemp;
	
	@Value("${file.location.upload}")
	private String locationUpload;
	
	@Autowired
	private GoodsEntityRepository repo;
	
	@Autowired
	private MemberEntityRepository mrepo;
	
	@Autowired
	private GoodsImgEntityRepository giRepository;
	
	@Transactional
	@Override
	public void update(long gno, AdminUpdateDTO dto) {
		System.out.println(">>>>>>>>>>>>수정처리 실행");
		GoodsEntity entityImg=null;
		Optional<GoodsEntity> result= repo.findById(gno);
		if(result.isPresent()) {
			GoodsEntity entity=result.get();
			entity.update(dto);
			entityImg =repo.save(entity);
			//이미지 디비내용물 삭제 작동안함
			//이미지 저장 전에 삭제
			giRepository.deleteByGoods_gno(gno);
			//이미지 저장
			dto.toItemListImgs(entityImg, locationUpload).forEach(giRepository::save);
		}
		
	}
	//삭제
	@Override
	public void delete(long gno) {
		repo.deleteById(gno);
		
	}
	
	//admin회원리스트
	@Override
	public void findAll(Model model) {
		List<MemberListInsertDTO> result = mrepo.findAll().stream()
				.map(MemberListInsertDTO::new).collect(Collectors.toList());
		
		model.addAttribute("list", result);
		
	}
	
}
