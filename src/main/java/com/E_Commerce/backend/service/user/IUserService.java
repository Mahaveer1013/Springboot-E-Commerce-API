package com.E_Commerce.backend.service.user;

import com.E_Commerce.backend.model.Users;
import com.E_Commerce.backend.request.UserDto;
import com.E_Commerce.backend.response.ApiResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    ResponseEntity<ApiResponse> addUser(UserDto user);
    
    ResponseEntity<ApiResponse> getUser(Long id);

    ResponseEntity<ApiResponse> getAllUser(Pageable pageable);

    ResponseEntity<ApiResponse> updateUser(Long id, UserDto userDto);

    ResponseEntity<ApiResponse> deleteUser(Long id);

    ResponseEntity<ApiResponse> verify(Users user);
}
