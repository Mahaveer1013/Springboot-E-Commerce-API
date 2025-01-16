package com.E_Commerce.backend.repository;

import com.E_Commerce.backend.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
