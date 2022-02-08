package com.example.awstest.security;

import com.example.awstest.domain.User;
import com.example.awstest.repository.UserRepository;
import com.example.awstest.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailServiceImpl implements UserDetailsService {
    private UserService userService;

    public UserDetailServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userService.getUserByName(username);
        if(!user.isPresent()) {
            throw new UsernameNotFoundException(username);
        }

        return user.get();
    }
}
