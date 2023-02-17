package com.green.nowon.domain.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsImgEntityRepository extends JpaRepository<GoodsImgEntity, Long>{
	
	Optional<GoodsImgEntity> findByUrlAndOrgName(String pATH, String fileName);
	void deleteByUrlAndNewName(String pATH, String fileName);
	void deleteByGoods_gno(long gno);
}