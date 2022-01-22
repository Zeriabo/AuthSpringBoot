package com.finnplay.springboot.repository;

import com.finnplay.springboot.model.User;

public interface UserCustomRepository {
User getUser(String username,String password);
}
