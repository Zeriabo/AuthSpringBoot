package com.finnplay.springboot.Iml;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.finnplay.springboot.model.User;
import com.finnplay.springboot.repository.UserCustomRepository;
@Repository

public class UserRepositoryImpl implements UserCustomRepository {
EntityManager entityManager;

@Override
public User getUser(String username,String password)
{
	 Query query = entityManager.createNativeQuery( "Select * from user where username = ? And password = ?",User.class);
	  query.setParameter(1, username + "%");
	  query.setParameter(2, password + "%");
	  
	  return (User) query.getSingleResult();
	
}
}
