package com.E_Commerce.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "user_details")
public class UserInfo extends BaseSchema {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    private Users userId;

    @NotNull(message = "First name can't be empty")
    private String firstName;

    @NotNull(message = "Last name can't be empty")
    private String lastName;

    @NotNull(message = "Mobile number can't be empty")
    private String mobile;

    private String telephone;

    @NotNull(message = "City can't be empty")
    private String city;

    @NotNull(message = "State can't be empty")
    private String postalCode;

    @NotNull(message = "Country can't be empty")
    private String country;

    @NotNull(message = "Address line 1 can't be empty")
    private String addressLine1;

    private String addressLine2;

}
