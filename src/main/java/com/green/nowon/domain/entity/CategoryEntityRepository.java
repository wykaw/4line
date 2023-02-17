package com.green.nowon.domain.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryEntityRepository extends JpaRepository<CategoryEntity, Long>{
	
	Optional<CategoryEntity> findByCname(String categoryName);
	
	//상위 카테고리가 null인 카테고리 -> 1차 카테고리
	List<CategoryEntity> findAllByParentCnoIsNull();
	
	//서브 카테고리
	List<CategoryEntity> findAllByParentCno(long parentNo);

	

}
