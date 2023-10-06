package com.apps.trip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRequest {
    private String username;

    private String email;

    private String fullName;

    private String phoneNumber;

    private String password;

    private String role;

    private List<String> favorite;
}
