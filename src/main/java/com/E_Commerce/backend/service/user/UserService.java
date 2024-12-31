package com.E_Commerce.backend.service.user;

import com.E_Commerce.backend.lib.enums.UserRole;
import com.E_Commerce.backend.lib.exception.InvalidEnumException;
import com.E_Commerce.backend.lib.exception.UserNotFoundException;
import com.E_Commerce.backend.mapper.UserMapper;
import com.E_Commerce.backend.model.Users;
import com.E_Commerce.backend.repository.UserRepository;
import com.E_Commerce.backend.request.LoginDto;
import com.E_Commerce.backend.request.UserDto;
import com.E_Commerce.backend.response.ApiResponse;
import com.E_Commerce.backend.service.utils.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;

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
    public ResponseEntity<ApiResponse> addUser(UserDto user) {
        try {
            Users newUser = userMapper.userDto_to_user(user);
            newUser.setPassword(encoder.encode(newUser.getPassword()));
            if (userRepository.existsByUsername(newUser.getUsername())) {
                return ResponseEntity.status(400).body(new ApiResponse("User already exists with the given username", null));
            }
            if (userRepository.existsByEmail(newUser.getEmail())) {
                return ResponseEntity.status(400).body(new ApiResponse("User already exists with the given email", null));
            }
            userRepository.save(newUser);
            return ResponseEntity.status(200).body(new ApiResponse("User Added Successfully", null));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Database Constraint Violation", null));
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(400).body(new ApiResponse("Constraint violation in the data provided.", null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(new ApiResponse("Invalid user data provided", null));
        } catch (InvalidEnumException e) {
            return ResponseEntity.status(400).body(new ApiResponse("Invalid role provided", null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse("An unexpected error occurred. Please try again later.", null));
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getUser(Long id) {
        Users user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        return ResponseEntity.status(400).body(new ApiResponse(null, userMapper.user_to_userDto(user)));
    }

    @Override
    public Users finduser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
    }

    @Override
    public ResponseEntity<ApiResponse> getAllUser(Pageable pageable) {
        try {
            Page<Users> userPage = userRepository.findAll(pageable);
            return ResponseEntity.status(400).body(new ApiResponse(null, userPage.getContent().stream().map(userMapper::user_to_userDto).toList()));
        } catch (Exception e) {
            throw new RuntimeException("Error while getting all users: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ApiResponse> updateUser(Long id, UserDto userDto) {
        try {
            Users user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
            userRepository.findByEmail(userDto.email()).ifPresent(existingUser -> {
                throw new DataIntegrityViolationException("User already exists with the given email");
            });
            if (userDto.email() != null) {
                user.setEmail(userDto.email());
            }
            if (userDto.username() != null) {
                user.setUsername(userDto.username());
            }
            if (userDto.role() != null) {
                try {
                    user.setRole(UserRole.valueOf(userDto.role().toUpperCase()));
                } catch (IllegalArgumentException ex) {
                    throw new IllegalArgumentException("Invalid role value: " + userDto.role());
                }
            }
            userRepository.save(user);
            return ResponseEntity.status(200).body(new ApiResponse("User Updated Successfully", null));
        } catch (Exception e) {
            throw new RuntimeException("Error while updating user: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ApiResponse> deleteUser(Long id) {
        try {
            userRepository.findById(id).ifPresentOrElse(userRepository::delete, () -> {
                throw new UserNotFoundException("User Not Found");
            });
            return ResponseEntity.status(400).body(new ApiResponse("User Deleted Successfully", null));
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting user: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ApiResponse> verify(LoginDto user) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.username(), user.password()));
            if (authentication.isAuthenticated()) {
                Users userData = userRepository.findByUsername(user.username());
                return ResponseEntity.status(200).body(new ApiResponse("User Login Success", jwtService.generateToken(userData.getId(), userData.getUsername(), userData.getEmail(), userData.getRole().toString())));
            } else return ResponseEntity.status(401).body(new ApiResponse("Credentials Not Matched", null));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(401).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
