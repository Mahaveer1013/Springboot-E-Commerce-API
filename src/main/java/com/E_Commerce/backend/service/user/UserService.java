package com.E_Commerce.backend.service.user;

import com.E_Commerce.backend.dto.users.UserMapper;
import com.E_Commerce.backend.dto.users.UserRequest;
import com.E_Commerce.backend.dto.users.UserResponse;
import com.E_Commerce.backend.lib.enums.UserRole;
import com.E_Commerce.backend.lib.exception.InvalidPasswordException;
import com.E_Commerce.backend.lib.exception.MissingFieldsException;
import com.E_Commerce.backend.lib.exception.UserAlreadyExistsException;
import com.E_Commerce.backend.lib.exception.UserNotFoundException;
import com.E_Commerce.backend.model.PaginatedResponse;
import com.E_Commerce.backend.model.Users;
import com.E_Commerce.backend.repository.UserRepository;
import com.E_Commerce.backend.service.utils.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse addUser(UserRequest user) {
        List<String> missingFields = new ArrayList<>();

        // Check for missing fields and collect them
        if (user.getUsername() == null) missingFields.add("username");
        if (user.getPassword() == null) missingFields.add("password");
        if (user.getRole() == null) missingFields.add("role");
        if (user.getEmail() == null) missingFields.add("email");

        // If there are any missing fields, throw an exception with details
        if (!missingFields.isEmpty()) {
            throw new MissingFieldsException("Missing required fields: " + String.join(", ", missingFields));
        }

        // Check password length
        if (user.getPassword().length() < 8) {
            throw new InvalidPasswordException("Password length must be at least 8 characters.");
        }

        // Check if user already exists by username
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException("User already exists with the given username.");
        }

        // Check if user already exists by email
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("User already exists with the given email.");
        }

        // Map UserRequest to Users entity
        Users newUser = userMapper.toEntity(user);

        // Password encoding
        newUser.setPassword(encoder.encode(newUser.getPassword()));

        // Save new user to the database
        userRepository.save(newUser);

        // Return mapped UserResponse
        return userMapper.toResponse(newUser);
    }

    @Override
    public UserResponse finduser(Long id) {
        Users user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        return userMapper.toResponse(user);
    }

    @Override
    public PaginatedResponse<UserResponse> getAllUser(Pageable pageable) {
        Page<Users> userPage = userRepository.findAll(pageable);

        List<UserResponse> userResponses = userPage.getContent()
                .stream()
                .map(userMapper::toResponse)
                .toList();

        return new PaginatedResponse<>(
                userResponses,
                userPage.getNumber(),
                userPage.getSize(),
                userPage.getTotalElements(),
                userPage.getTotalPages(),
                userPage.isLast()
        );
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        Users user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        userRepository.findByEmail(userRequest.getEmail()).ifPresent(existingUser -> {
            throw new DataIntegrityViolationException("User already exists with the given email");
        });

        if (userRequest.getEmail() != null) {
            user.setEmail(userRequest.getEmail());
        }
        if (userRequest.getUsername() != null) {
            user.setUsername(userRequest.getUsername());
        }
        if (userRequest.getRole() != null) {
            try {
                user.setRole(UserRole.valueOf(userRequest.getRole().toString().toUpperCase()));
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("Invalid role value: " + userRequest.getRole());
            }
        }
        userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).ifPresentOrElse(userRepository::delete, () -> {
            throw new UserNotFoundException("User Not Found");
        });
    }


}
