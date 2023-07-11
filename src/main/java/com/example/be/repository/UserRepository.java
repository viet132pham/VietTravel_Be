package com.example.be.repository;

import com.example.be.entity.Role;
import com.example.be.entity.User;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends BaseRepository<User, Long>{
    List<User> findUserByRoles(Role role);
    User findUserById(long id);
    @Modifying
    @Query(value = "delete from user_role where user_id=:uid and role_id=:rid", nativeQuery = true)
    void deleteUserRole(long uid, long rid);

    @Modifying
    @Query(value = "select * from user where username=:username", nativeQuery = true)
    List<User> findUserByUsername(String username);

}