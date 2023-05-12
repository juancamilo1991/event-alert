package com.projects.eventsapp.user.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.projects.eventsapp.blog.utils.JsonViewProfiles;
import com.projects.eventsapp.errorHandling.AlreadyFollowingUserException;
import com.projects.eventsapp.errorHandling.UserAlreadyExistException;
import com.projects.eventsapp.user.dto.UserDto;
import com.projects.eventsapp.user.model.User;
import com.projects.eventsapp.user.service.UserDetailsServiceImpl;
import com.projects.eventsapp.user.service.UserService;
import com.projects.eventsapp.user.utils.UserUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserDetailsServiceImpl userDetailsService;
    private final UserUtils userUtils;

    public UserController(UserService userService, UserDetailsServiceImpl userDetailsService, UserUtils userUtils) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.userUtils = userUtils;
    }

    @GetMapping(path = "/all-users")
    @JsonView(JsonViewProfiles.UserDetails.class)
    public List<UserDto> getAllUsers() {
        return UserUtils.mapToUserDtos(userService.getUsers());
    }

    @PostMapping(path = "/register")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDto userDto) {
        try {
            userService.registerNewUserAccount(userDto);
            return ResponseEntity.
                    ok("registration successful");
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(path = "/{id}")
    @JsonView(JsonViewProfiles.UserDetails.class)
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.ok().body(new UserDto(user));
        }
        catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(path = "/my-information")
    @JsonView(JsonViewProfiles.UserDetails.class)
    public UserDto getPersonalInformation(Principal principal) {
        return UserUtils.mapToUserDto(userService.getStoredUser(principal));
    }

    @GetMapping(path = "/follow/{userId}")
    public ResponseEntity<String> followUser(Principal principal, @PathVariable Long userId) {
        User followedUser = userService.getUserById(userId);
        User followingUser = userService.getStoredUser(principal);
        // look if already following
        try {
            userUtils.addFollower(followingUser, followedUser);
            userService.updateUser(followingUser);
            return ResponseEntity.ok("following user " + followedUser.getUsername());
        }
        catch (AlreadyFollowingUserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
