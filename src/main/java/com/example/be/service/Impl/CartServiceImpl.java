package com.example.be.service.Impl;

import com.example.be.dto.CartDTO;
import com.example.be.dto.CartitemDTO;
import com.example.be.dto.UserDTO;
import com.example.be.entity.Cart;
import com.example.be.entity.Cartitem;
import com.example.be.entity.User;
import com.example.be.repository.BaseRepository;
import com.example.be.repository.CartRepository;
import com.example.be.repository.CartitemRepository;
import com.example.be.repository.UserRepository;
import com.example.be.request.CartRequest;
import com.example.be.response.CartitemStatus;
import com.example.be.service.CartService;
import com.example.be.util.Utils;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class CartServiceImpl extends BaseServiceImpl<Cart> implements CartService {
    public CartServiceImpl(BaseRepository<Cart, Long> baseRepo, Utils utils) {
        super(baseRepo, utils);
    }

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartitemRepository cartitemRepository;

    @Autowired
    private Utils utils;

    @Autowired
    private UserRepository userRepository;
    public Cart createRequest(CartRequest cartRequest, BindingResult bindingResult){
        Cart cart = new Cart();
        mapper.map(cartRequest, cart);
        cart.setUser(userRepository.findUserById(cartRequest.getUserId()));
        return cartRepository.save(cart);
    }

    public Cart updateCart(long id, CartRequest cartRequest, BindingResult bindingResult) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(("id not found: " + id)));
        BindingResult result = utils.getListResult(bindingResult, cartRequest);
        if (result.hasErrors()) {
            throw utils.invalidInputException(result);
        } else {
            mapper.map(cartRequest, cart);
            return cartRepository.save(cart);
        }
    }

    public CartDTO getCart(long userId){
        User user = userRepository.findUserById(userId);
        Cart cart = cartRepository.findCartByUserAndStatus(user, "WAITING");
        if (cart == null) {
            return null;
        } else {
            CartDTO cartDTO = new CartDTO();
            UserDTO userDTO = new UserDTO();
            mapper.map(cart, cartDTO);
            mapper.map(cart.getUser(), userDTO);
            cartDTO.setUserDTO(userDTO);
            return cartDTO;
        }
    }

    public List<CartitemDTO> getItems(long cartId) {
        List<Long> cartitems = cartitemRepository.findAllItems(cartId);

        List<Cartitem> result = new ArrayList<>();
        cartitems.forEach(cartitem -> {
                        result.add(cartitemRepository.findById(cartitem).orElseThrow(() -> new IllegalArgumentException(("id not found: " + cartitem))));
                    });
        List<CartitemDTO> cartitemDTOList = new ArrayList<>();
        for (int j = 0; j < result.size(); j++){
            UserDTO userDTO = new UserDTO();
            CartDTO cartDTO = new CartDTO();
            CartitemDTO cartitemDTO = new CartitemDTO();
            mapper.map(result.get(j), cartitemDTO);
            mapper.map(result.get(j).getCart(), cartDTO);
            mapper.map(result.get(j).getCart().getUser(), userDTO);
            cartDTO.setUserDTO(userDTO);
            cartitemDTO.setCartDTO(cartDTO);
            cartitemDTOList.add(cartitemDTO);
        }
        return cartitemDTOList;
    }

    public List<CartitemStatus> getListOrdered(long userId) {
        List<Long> cartitems = cartitemRepository.findListItemsOrdered(userId);

        List<CartitemStatus> result = new ArrayList<>();
        cartitems.forEach(cartitem -> {
            result.add(cartitemRepository.findByIdCartitem(cartitem));
        });
        return result;
    }

}
