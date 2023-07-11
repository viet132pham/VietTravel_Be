package com.example.be.service;

import com.example.be.dto.CartDTO;
import com.example.be.dto.CartitemDTO;
import com.example.be.entity.Cart;
import com.example.be.request.CartRequest;
import com.example.be.response.CartitemStatus;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface CartService extends BaseService<Cart> {

    Cart createRequest(CartRequest cartRequest, BindingResult bindingResult);

    Cart updateCart(long id, CartRequest cartRequest, BindingResult bindingResult);

    CartDTO getCart(long userId);

    List<CartitemDTO> getItems(long cartId);

    List<CartitemStatus> getListOrdered(long userId);
}
