package com.apps.trip.service;

import com.apps.trip.dto.ChangeInfoRequest;
import com.apps.trip.dto.UserDto;
import com.apps.trip.models.User;
import com.apps.trip.repository.UserRepository;
import com.apps.trip.utils.AppsUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<UserDto> getUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(user ->
                new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getFullName(), user.getPhoneNumber())
        );
    }

    @Override
    public UserDto getInfoUser(String username) {
        Optional<User> userOptional = getUser(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getFullName(), user.getPhoneNumber());
        }
        return new UserDto();
    }

    @Override
    public UserDto findById(Long id) {
        Optional<User> userOptional = getUserById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getFullName(), user.getPhoneNumber());
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

    @Override
    public boolean changeInfoById(Long id, ChangeInfoRequest request) {
        Optional<User> userOptional = getUserById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setFullName(request.getFullName());
            user.setPhoneNumber(request.getPhoneNumber());
            userRepository.save(user);

            return true;
        }
        return false;
    }

    private Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<User> userOptional = getUserById(id);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());

            return true;
        }
        return false;
    }
}
