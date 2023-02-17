package com.green.nowon.domain.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartEntityRepository extends JpaRepository<CartEntity, Long>{

	Optional<CartEntity> findByMemberEmail(String email);

}
