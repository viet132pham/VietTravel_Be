package com.example.be.repository;

import com.example.be.entity.Cart;
import com.example.be.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends BaseRepository<Cart, Long> {
    public Cart findCartByUserAndStatus(User user, String status);

}
