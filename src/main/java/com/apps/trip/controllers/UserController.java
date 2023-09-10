package com.apps.trip.controllers;

import com.apps.trip.dto.ChangeInfoRequest;
import com.apps.trip.dto.UserDto;
import com.apps.trip.dto.UserRequest;
import com.apps.trip.service.UserService;
import com.apps.trip.utils.AppsUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PutMapping
    public ResponseEntity<Boolean> changeInfoUser(@RequestBody ChangeInfoRequest request) {
        return ResponseEntity.ok(userService.changeInfo(request));
    }

    @PostMapping("filter")
    public ResponseEntity<Page<UserDto>> getListUsers(@RequestParam("page") int page, @RequestParam("size") int size) {
        return ResponseEntity.ok(userService.getUsers(PageRequest.of(page, size)));
    }

    @PostMapping("{id}")
    public ResponseEntity<Boolean> changeInfoById(@PathVariable("id") Long id, @RequestBody ChangeInfoRequest request) {
        return ResponseEntity.ok(userService.changeInfoById(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteInfoById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.deleteById(id));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Boolean> save(@RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.save(request));
    }
}
