package com.E_Commerce.backend.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record StudentDto(
        Integer userId,

        @NotNull(message = "First name cannot be null")
        String firstName,

        @NotNull(message = "Last name cannot be null")
        String lastName,

        @NotNull(message = "Gender cannot be null")
        String gender,

        @NotNull(message = "Roll number cannot be null")
        String rollNumber,

        @NotNull(message = "Register number cannot be null")
        String registerNumber,

        @NotNull(message = "Phone number cannot be null")
        String phoneNumber,

        @NotNull(message = "Address cannot be null")
        String address,

        @NotNull(message = "Department cannot be null")
        String departmentId,

        @NotNull(message = "Date of birth cannot be null")
        LocalDate dob,

        @NotNull(message = "Admission year cannot be null")
        int admissionYear,

        @NotNull(message = "Graduation year cannot be null")
        int graduationYear
) {
}
