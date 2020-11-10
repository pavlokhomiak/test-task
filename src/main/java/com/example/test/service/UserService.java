package com.example.test.service;

import com.example.test.model.User;
import java.util.List;

public interface UserService {

    User register(User user);

    User getById(Long id);

    List<User> getAll();

    void updateAdmin(Long id, boolean admin);

    void updateActive(Long id, boolean active);

    void delete(Long id);
}
