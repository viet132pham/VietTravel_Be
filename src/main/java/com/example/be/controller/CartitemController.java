package com.example.be.controller;

import com.example.be.dto.CartitemDTO;
import com.example.be.entity.Cartitem;
import com.example.be.repository.CartitemRepository;
import com.example.be.request.CartitemRequest;
import com.example.be.service.CartitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/cartitem")
public class CartitemController {
    @Autowired
    private CartitemRepository cartitemRepository;

    @Autowired
    private CartitemService cartitemService;

    @PostMapping("/post")
    public ResponseEntity<?> postRequest(@RequestBody @Valid CartitemRequest cartitemRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Validation failed", HttpStatus.BAD_REQUEST);
        }

        try {
            String result = cartitemService.createRequest(cartitemRequest, bindingResult);
            if (result.equals("oke")) {
                return new ResponseEntity<>("Success", HttpStatus.OK);
            } else if (result.startsWith("id not found")) {
                return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>("Fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // remove san pham theo id khoi cart
    @DeleteMapping("/delete/{id}")
    public void deleteCartitem(@PathVariable(value = "id") long id){
        cartitemService.deleteCartitem(id);
    }

    // Truyền vào cartId, từ đó get ra list id của item rồi xóa.
    @DeleteMapping("/delete_all/{cartId}")
    public ResponseEntity<String> deleteAllCartitem(@PathVariable(value = "cartId") long cartId) {
        try {
            cartitemService.deleteAllCartitem(cartId);
            return ResponseEntity.ok("Xóa thành công");
        } catch (Exception e) {
            // Xử lý ngoại lệ và trả về thông báo lỗi
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi: " + e.getMessage());
        }
    }

    // add san pham, tang quantity
    @PutMapping("/put/{quantity}/{itemId}")
    public Cartitem updateQuantityCart(@PathVariable(value = "quantity") int quantity, @PathVariable(value = "itemId") long itemId) {
        return cartitemService.updateQuantityCart(quantity, itemId);
    }

    @GetMapping("/get/{id}")
    public CartitemDTO getCartitemById(@PathVariable(value = "id") long id){
        return cartitemService.getCartitemById(id);
    }

}