package com.projects.eventsapp.user.service;

import com.projects.eventsapp.errorHandling.UserAlreadyExistException;
import com.projects.eventsapp.user.dto.UserDto;
import com.projects.eventsapp.user.model.User;
import com.projects.eventsapp.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CustomPasswordEncoder customPasswordEncoder;
    private final UserDetailsServiceImpl userDetailsService;

    public UserService(UserRepository userRepository, CustomPasswordEncoder customPasswordEncoder, UserDetailsServiceImpl userDetailsService) {
        this.userRepository = userRepository;
        this.customPasswordEncoder = customPasswordEncoder;
        this.userDetailsService = userDetailsService;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String username) throws UsernameNotFoundException {
       return (User) userDetailsService.loadUserByUsername(username);
    }

    public User getUserById(Long id) throws UsernameNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    public User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException {
                if (emailExists(userDto.getEmail())) {
                    throw new UserAlreadyExistException("There is an account with that email address: "
                            + userDto.getEmail());
                }
                if (usernameExists(userDto.getUsername())) {
                    throw new UserAlreadyExistException("There is an account with that username: "
                    + userDto.getUsername());
                }
                User user = new User();
                user.setUsername(userDto.getUsername());
                user.setEmail(userDto.getEmail());
                user.setPassword(customPasswordEncoder.encoder().encode(userDto.getPassword()));

                return userRepository.save(user);
    }



    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public Optional<User> isAlreadyLiked(Long userId, Long postId) {
        return userRepository.findByAlreadyLiked(userId, postId);
    }

    public Optional<User> isAlreadyFollowing(Long followerId, Long followingId) {
        return userRepository.findByAlreadyFollowing(followerId, followingId);
    }

    public User getStoredUser (Principal principal) {
        return userDetailsService.getUserByUsername(principal);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

}
