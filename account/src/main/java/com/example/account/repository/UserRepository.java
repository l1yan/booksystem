package com.example.account.repository;

import com.example.account.entity.User;

public interface UserRepository {
    public User login(String username, String password);
}
