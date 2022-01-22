package com.finnplay.springboot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.finnplay.springboot.model.User;
 
public interface UserRepository extends JpaRepository<User, Long> {
	 @Query("SELECT u FROM User u WHERE u.username = ?1 And u.password = ?2")
	    public User signin(String username, String password);
}