package com.apps.trip.controllers;

import com.apps.trip.dto.ChangeInfoRequest;
import com.apps.trip.dto.UserDto;
import com.apps.trip.service.UserService;
import com.apps.trip.utils.AppsUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("info")
    public ResponseEntity<UserDto> getInfoUser() {
        UserDto user = userService.getInfoUser(AppsUtils.getUsername());
        return ResponseEntity.ok(user);
    }

    @PostMapping("change-info")
    public ResponseEntity<Boolean> changeInfoUser(@RequestBody ChangeInfoRequest request) {
        return ResponseEntity.ok(userService.changeInfo(request));
    }
}
