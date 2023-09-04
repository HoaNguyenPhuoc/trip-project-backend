package com.apps.trip.service;

import com.apps.trip.dto.ChangeInfoRequest;
import com.apps.trip.dto.UserDto;

public interface UserService {
    UserDto getInfoUser(String username);

    boolean changeInfo(ChangeInfoRequest request);
}
