package com.apps.trip.service;

import com.apps.trip.dto.ChangeInfoRequest;
import com.apps.trip.dto.UserDto;
import com.apps.trip.models.User;
import com.apps.trip.repository.UserRepository;
import com.apps.trip.utils.AppsUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto getInfoUser(String username) {
        Optional<User> userOptional = getUser(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return new UserDto(user.getUsername(), user.getEmail(), user.getFullName(), user.getPhoneNumber());
        }
        return new UserDto();
    }

    private Optional<User> getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean changeInfo(ChangeInfoRequest request) {
        Optional<User> userOptional = getUser(AppsUtils.getUsername());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setFullName(request.getFullName());
            user.setPhoneNumber(request.getPhoneNumber());
            userRepository.save(user);

            return true;
        }
        return false;
    }
}
