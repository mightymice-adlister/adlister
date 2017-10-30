package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;

import java.util.List;

public interface Users {
    User findByUsername(String username);
    Long insert(User user);
    void updateProfile(User user, String newEmail, String firstName, String lastName, String dayNumber, String eveningNumber, String bio);
    void changePassword(User user, String newPassword);
}
