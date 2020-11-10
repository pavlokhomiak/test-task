package com.example.test.service.impl;

import com.example.test.annotations.LogMethod;
import com.example.test.model.Account;
import com.example.test.model.User;
import com.example.test.repository.UserRepository;
import com.example.test.service.AccountService;
import com.example.test.service.UserService;
import java.util.List;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = {"userCache"})
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           AccountService accountService,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
    }

    @LogMethod
    @CachePut(key = "#user")
    @Override
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles("USER");
        user.setActive(1);
        User userFromDb = userRepository.save(user);
        Account account = new Account();
        account.setUser(userFromDb);
        accountService.add(account);
        return userFromDb;
    }

    @LogMethod
    @Override
    public User getById(Long id) {
        return userRepository.getOne(id);
    }

    @LogMethod
    @Cacheable
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @LogMethod
    @Override
    public void updateAdmin(Long id, boolean admin) {
        User userFromDb = userRepository.getOne(id);
        if (admin) {
            userFromDb.setRoles("USER,ADMIN");
        } else {
            userFromDb.setRoles("USER");
        }
        userRepository.save(userFromDb);
    }

    @LogMethod
    @Override
    public void updateActive(Long id, boolean active) {
        User userFromDb = userRepository.getOne(id);
        if (active) {
            userFromDb.setActive(1);
        } else {
            userFromDb.setActive(0);
        }
        userRepository.save(userFromDb);
    }

    @LogMethod
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
