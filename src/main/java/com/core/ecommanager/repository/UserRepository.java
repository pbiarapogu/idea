package com.core.ecommanager.repository;

import com.core.ecommanager.model.User;
import com.core.ecommanager.model.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends CrudRepository<UserDao, Integer> {
//    List<User> findAll();

    UserDao findByUsername(String username);
}
