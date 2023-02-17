package com.green.nowon.domain.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartGoodsEntityRepository extends JpaRepository<CartGoodsEntity, Long> {

	Optional<CartGoodsEntity> findByCartNoAndGoodsGno(long cartNo, long goodsGno);

	List<CartGoodsEntity> findAllByCartMemberEmail(String email);

}
