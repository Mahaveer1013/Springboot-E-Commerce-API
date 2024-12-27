//package com.E_Commerce.backend.service.student;
//
//import com.E_Commerce.backend.lib.enums.Gender;
//import com.E_Commerce.backend.mapper.StudentMapper;
//import com.E_Commerce.backend.repository.StudentRepository;
//import com.E_Commerce.backend.request.StudentDto;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class StudentService {
//
//    private final StudentRepository studentRepository;
//    private final StudentMapper studentMapper;
//
//    public StudentService(
//            StudentRepository studentRepository,
//            StudentMapper studentMapper) {
//        this.studentRepository = studentRepository;
//        this.studentMapper = studentMapper;
//    }
//
//    public StudentDto addStudent(StudentDto studentDto) {
//        Categories student = studentMapper.toEnity(studentDto);
//        Categories savedStudent = studentRepository.save(student);
//        return studentMapper.toDto(savedStudent);
//    }
//
//    public StudentDto getStudent(Integer id) {
//        Categories student = studentRepository.findById(id).orElse(null);
//        if (student == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Does Not Exists");
//        }
//        return studentMapper.toDto(student);
//    }
//
//    public List<StudentDto> getAllStudent(Pageable pageable) {
//        Page<Categories> studentPage = studentRepository.findAll(pageable);
//        return studentPage.getContent()
//                .stream()
//                .map(studentMapper::toDto)
//                .toList();
//    }
//
//    public String updateStudent(Integer id, StudentDto studentDto) {
//        Categories student = studentRepository.findById(id).orElse(null);
//        if (student == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Does Not Exists");
//        }
//        if (studentDto.firstName() != null) {
//            student.setFirstName(studentDto.firstName());
//        }
//        if (studentDto.lastName() != null) {
//            student.setLastName(studentDto.lastName());
//        }
//        if (studentDto.gender() != null) {
//            try {
//                student.setGender(Gender.valueOf(studentDto.gender().toUpperCase()));
//            } catch (IllegalArgumentException ex) {
//                throw new IllegalArgumentException("Invalid gender value: " + studentDto.gender());
//            }
//        }
//        if (studentDto.rollNumber() != null) {
//            student.setRollNumber(studentDto.rollNumber());
//        }
//        if (studentDto.registerNumber() != null) {
//            student.setRegisterNumber(studentDto.registerNumber());
//        }
//        if (studentDto.phoneNumber() != null) {
//            student.setPhoneNumber(studentDto.phoneNumber());
//        }
//        if (studentDto.address() != null) {
//            student.setAddress(studentDto.address());
//        }
//        if (studentDto.departmentId() != null) {
//            student.setDepartmentId(studentDto.departmentId());
//        }
//        if (studentDto.dob() != null) {
//            student.setDob(studentDto.dob());
//        }
//        if (studentDto.admissionYear() != 0) {
//            student.setAdmissionYear(studentDto.admissionYear());
//        }
//        if (studentDto.graduationYear() != 0) {
//            student.setGraduationYear(studentDto.graduationYear());
//        }
//        return "Student Updated Successfully";
//    }
//
//    public String deleteStudent(Integer id) {
//        Optional<Categories> studentOptional = studentRepository.findById(id);
//        if (studentOptional.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Does Not Exists");
//        }
//
//        studentRepository.delete(studentOptional.get());
//        return "Student Deleted Successfully";
//    }
//
//}
