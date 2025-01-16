package com.E_Commerce.backend.service.user;

import com.E_Commerce.backend.dto.users.UserRequest;
import com.E_Commerce.backend.dto.users.UserResponse;
import com.E_Commerce.backend.model.PaginatedResponse;
import org.springframework.data.domain.Pageable;

public interface IUserService {

    UserResponse addUser(UserRequest user);

    UserResponse finduser(Long id);

    PaginatedResponse<UserResponse> getAllUser(Pageable pageable);

    UserResponse updateUser(Long id, UserRequest userDto);

    void deleteUser(Long id);

}
