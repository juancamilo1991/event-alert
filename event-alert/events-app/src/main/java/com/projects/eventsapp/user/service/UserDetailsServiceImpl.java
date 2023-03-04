package com.projects.eventsapp.user.service;


import com.projects.eventsapp.user.UserRepository;
import com.projects.eventsapp.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByUsername(username);
        return userOpt.orElseThrow(() -> new UsernameNotFoundException("not found"));
    }

    public User getUserByUsername (Principal principal) throws UsernameNotFoundException {
        String username = principal.getName();
        return (User) loadUserByUsername(username);
    }

}
