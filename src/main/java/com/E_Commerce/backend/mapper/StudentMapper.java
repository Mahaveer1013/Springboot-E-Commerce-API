//package com.E_Commerce.backend.mapper;
//
//import com.E_Commerce.backend.lib.enums.Gender;
//import com.E_Commerce.backend.lib.exception.InvalidEnumException;
//import com.E_Commerce.backend.lib.exception.UserNotFoundException;
//import com.E_Commerce.backend.model.Users;
//import com.E_Commerce.backend.repository.UserRepository;
//import com.E_Commerce.backend.request.StudentDto;
//import org.springframework.stereotype.Service;
//
//@Service
//public class StudentMapper {
//
//    private final UserRepository userRepository;
//
//    public StudentMapper(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//
//    // Converts StudentDto to Student entity
//    public Categories toEnity(StudentDto dto) {
//        var student = new Categories();
//
//        Users user = userRepository.findById(dto.userId())
//                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
//
//        student.setUserId(user);
//        student.setFirstName(dto.firstName());
//        student.setLastName(dto.lastName());
//        student.setGender(dto.gender() != null ? Gender.valueOf(dto.gender().toUpperCase()) : null);
//        if (dto.gender() != null) {
//            try {
//                student.setGender(Gender.valueOf(dto.gender().toUpperCase()));
//            } catch (IllegalArgumentException e) {
//                throw new InvalidEnumException("Invalid Gender provided: " + dto.gender());
//            }
//        }
//        student.setRollNumber(dto.rollNumber());
//        student.setRegisterNumber(dto.registerNumber());
//        student.setPhoneNumber(dto.phoneNumber());
//        student.setAddress(dto.address());
//        student.setDepartmentId(dto.departmentId());
//        student.setDob(dto.dob());
//        student.setAdmissionYear(dto.admissionYear());
//        student.setGraduationYear(dto.graduationYear());
//
//        return student;
//    }
//
//    // Converts Student entity to StudentDto
//    public StudentDto toDto(Categories student) {
//        return new StudentDto(
//                student.getUserId().getId(),
//                student.getFirstName(),
//                student.getLastName(),
//                student.getGender().name(),
//                student.getRollNumber(),
//                student.getRegisterNumber(),
//                student.getPhoneNumber(),
//                student.getAddress(),
//                student.getDepartmentId(),
//                student.getDob(),
//                student.getAdmissionYear(),
//                student.getGraduationYear()
//        );
//    }
//}
