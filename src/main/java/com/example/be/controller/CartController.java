package com.example.be.controller;

import com.example.be.dto.CartDTO;
import com.example.be.dto.CartitemDTO;
import com.example.be.entity.Cart;
import com.example.be.request.CartRequest;
import com.example.be.response.CartitemStatus;
import com.example.be.service.BaseService;
import com.example.be.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/cart")
public class CartController extends BaseController<Cart> {
    public CartController(BaseService<Cart> baseService) {
        super(baseService);
    }

    @Autowired
    private CartService cartService;


    // Tạo cart khi khách hàng đăng nhập2
    @PostMapping("/post")
    public Cart postRequest(@RequestBody @Valid CartRequest cartRequest, BindingResult bindingResult) {
        return cartService.createRequest(cartRequest, bindingResult);
    }

    // get lai cart sau khi dang nhap laị
    @PutMapping("/put/{cartId}")
    public Cart updateCart(@PathVariable(value = "cartId") long cartId, @RequestBody @Valid CartRequest cartRequest , BindingResult bindingResult) {
        return cartService.updateCart(cartId, cartRequest, bindingResult);
    }

    // khi login, tìm cart theo id user, check status cart (status cart nếu đã thanh toán thì là process, di choi về thì là done), nếu người dùng này có cart ở trạng thái waiting thì get ra để dùng, nếu không thì lại tạo cart bằng /post ở trên
    @GetMapping("/get/{userId}")
    public CartDTO getCart(@PathVariable(value = "userId") long userId) {
        return cartService.getCart(userId);
    }

    // trả về list cartitem, những item có trong cart
    @GetMapping("/getItems/{cartId}")
    public List<CartitemDTO> getItems(@PathVariable(value = "cartId") long cartId) {
        return cartService.getItems(cartId);
    }

    // get ra list những dịch vụ mà khách đã order, get theo user id, cái này nằm trong phần order ở account page
    @GetMapping("/get_list_ordered/{userId}")
    public List<CartitemStatus> getListOrdered(@PathVariable(value = "userId") long userId) {
        return cartService.getListOrdered(userId);
    }
}