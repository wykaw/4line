package com.green.nowon.service;

import org.springframework.ui.Model;

import com.green.nowon.domain.dto.cart.CartGoodsSaveDTO;

public interface CartService {

	void save(CartGoodsSaveDTO dto, String email);

	void cart(Model model, String email);

}
