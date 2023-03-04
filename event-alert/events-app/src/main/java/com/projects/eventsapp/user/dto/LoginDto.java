package com.projects.eventsapp.user.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.projects.eventsapp.blog.utils.JsonViewProfiles;

import javax.validation.constraints.NotBlank;

public class LoginDto {

        @NotBlank(message = "username must not be empty")
        @JsonView({JsonViewProfiles.UserDetails.class,
                   JsonViewProfiles.BlogPostChannel.class,
                   JsonViewProfiles.BlogPostDetail.class})
        private String username;
        @NotBlank(message = "password must not be empty")
        private String password;

        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
}
