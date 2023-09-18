package com.apps.trip.controllers;

import com.apps.trip.dto.ChangeInfoRequest;
import com.apps.trip.dto.UserDto;
import com.apps.trip.dto.UserRequest;
import com.apps.trip.payload.response.ResponseJson;
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
    public static final String SUCCESS = "Success";
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
    public ResponseEntity<ResponseJson> changeInfoUser(@RequestBody ChangeInfoRequest request) {
        userService.changeInfo(request);
        return ResponseEntity.ok(new ResponseJson(200, SUCCESS));
    }

    @PostMapping("filter")
    public ResponseEntity<Page<UserDto>> getListUsers(@RequestParam("page") int page, @RequestParam("size") int size) {
        return ResponseEntity.ok(userService.getUsers(PageRequest.of(page, size)));
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseJson> changeInfoById(@PathVariable("id") Long id, @RequestBody ChangeInfoRequest request) {
        userService.changeInfoById(id, request);
        return ResponseEntity.ok(new ResponseJson(200, SUCCESS));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseJson> deleteInfoById(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok(new ResponseJson(200, SUCCESS));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ResponseJson> save(@RequestBody UserRequest request) {
        userService.save(request);
        return ResponseEntity.ok(new ResponseJson(200, SUCCESS));
    }
}
