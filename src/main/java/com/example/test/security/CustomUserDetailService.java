package com.example.test.security;

import com.example.test.model.User;
import com.example.test.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name)
            throws UsernameNotFoundException {
        User user = userRepository.findByName(name).orElseThrow(
                () -> new UsernameNotFoundException("User not found."));

        org.springframework.security.core.userdetails.User.UserBuilder builder =
                org.springframework.security.core.userdetails.User.withUsername(name);
        builder.username(name);
        builder.password(user.getPassword());
        builder.disabled(user.getActive() == 0);
        String[] roles = user.getRoleList().toArray(new String[0]);
        builder.roles(roles);
        return builder.build();
    }
}
