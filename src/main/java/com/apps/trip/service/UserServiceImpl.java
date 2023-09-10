package com.apps.trip.service;

import com.apps.trip.dto.ChangeInfoRequest;
import com.apps.trip.dto.UserDto;
import com.apps.trip.dto.UserRequest;
import com.apps.trip.models.ERole;
import com.apps.trip.models.Role;
import com.apps.trip.models.User;
import com.apps.trip.repository.RoleRepository;
import com.apps.trip.repository.UserRepository;
import com.apps.trip.utils.AppsUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean save(UserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Set<Role> roles = new HashSet<>();
        if (request.getRole().equals("admin")) {
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);
        } else {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }
        user.setRoles(roles);

        return true;
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
    @Transactional
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
    @Transactional
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
    @Transactional
    public boolean deleteById(Long id) {
        Optional<User> userOptional = getUserById(id);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());

            return true;
        }
        return false;
    }
}
