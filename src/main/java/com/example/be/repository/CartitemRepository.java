package com.example.be.repository;

import com.example.be.entity.Cart;
import com.example.be.entity.Cartitem;
import com.example.be.response.CartitemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartitemRepository extends JpaRepository<Cartitem, Long>{
    @Modifying
    @Query(value = "SELECT ci.category_id " +
            "FROM cartitem ci " +
            "INNER JOIN cart c ON ci.cart_id = c.id " +
            "WHERE ci.category_name = :category " +
            "AND c.status = 'DONE' " +
            "GROUP BY ci.category_id " +
            "ORDER BY COUNT(ci.category_id) DESC " +
            "LIMIT 6", nativeQuery = true)
    List<Integer> findCategoryIdInCartitem(@Param("category") String category);

    @Modifying
    @Query(value = "SELECT ci.id\n" +
            "FROM cartitem ci\n" +
            "INNER JOIN cart c ON ci.cart_id = c.id " +
            "WHERE c.status = 'WAITING'\n" +
            "AND c.id = :cid", nativeQuery = true)
    List<Long> findAllItems(long cid);

    @Modifying
    @Query(value = "SELECT ci.id\n" +
            "FROM cartitem ci\n" +
            "INNER JOIN cart c ON ci.cart_id = c.id " +
            "WHERE c.status = 'PROCESS' OR c.status = 'DONE'\n" +
            "AND c.user_id = :uid", nativeQuery = true)
    List<Long> findListItemsOrdered(long uid);

    @Query(value = "SELECT ci.id as id, ci.quantity as quantity, ci.image as image, ci.name as name, c.status as status, ci.price as price, ci.category_id as categoryId, ci.category_name as categoryName\n" +
            "FROM cartitem ci\n" +
            "INNER JOIN cart c ON ci.cart_id = c.id " +
            "WHERE ci.id = :cid", nativeQuery = true)
    CartitemStatus findByIdCartitem(long cid);


    @Query(value = "SELECT *\n" +
            "FROM cartitem ci\n" +
            "WHERE ci.category_id = :id AND ci.category_name = :name AND ci.cart_id = :cid", nativeQuery = true)
    Cartitem findCartitemByCategoryIdAndAndCategoryName(long cid, long id, String name);

}
