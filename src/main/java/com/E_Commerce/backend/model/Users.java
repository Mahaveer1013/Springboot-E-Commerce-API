package com.E_Commerce.backend.model;

import com.E_Commerce.backend.lib.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Data
@Table(name = "users")
public class Users extends BaseSchema {

    @NotNull(message = "Username cannot be null")
    @Column(unique = true)
    private String username;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    @Column(unique = true)
    private String email;

    @NotNull(message = "Password cannot be null")
    @Size(min = 1, message = "Password must be at least 8 characters long")
    private String password;

    //    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role cannot be null")
    private UserRole role;

}
