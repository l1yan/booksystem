package com.example.account.repository;

import com.example.account.entity.Admin;

public interface AdminRepository {
    public Admin login(String username,String password);
}
