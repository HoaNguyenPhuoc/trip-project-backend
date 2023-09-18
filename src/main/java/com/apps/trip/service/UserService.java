package com.apps.trip.service;

import com.apps.trip.dto.ChangeInfoRequest;
import com.apps.trip.dto.UserDto;
import com.apps.trip.dto.UserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    boolean save(UserRequest request);
    Page<UserDto> getUsers(Pageable pageable);

    UserDto getInfoUser(String username);

    UserDto findById(Long id);

    void changeInfo(ChangeInfoRequest request);

    void changeInfoById(Long id, ChangeInfoRequest request);

    void deleteById(Long id);
}
