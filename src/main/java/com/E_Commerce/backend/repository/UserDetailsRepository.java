package com.E_Commerce.backend.repository;

import com.E_Commerce.backend.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
}
