//package com.E_Commerce.backend.controller;
//
//import com.E_Commerce.backend.request.StudentDto;
//import com.E_Commerce.backend.service.student.StudentService;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@RequestMapping("/student")
//public class StudentController {
//    private final StudentService studentService;
//
//    public StudentController(StudentService studentService) {
//        this.studentService = studentService;
//    }
//
//    @PostMapping
//    public StudentDto addStudent(
//            @Valid @RequestBody StudentDto studentDto) {
//        return studentService.addStudent(studentDto);
//    }
//
//    @GetMapping("/{id}")
//    public StudentDto getStudent(
//            @PathVariable("id") Integer id) {
//        return studentService.getStudent(id);
//    }
//
//    @GetMapping
//    public List<StudentDto> getAllStudent(
//            @PageableDefault(page = 0, size = 10) Pageable page
//    ) {
//        List<StudentDto> students = studentService.getAllStudent(page);
//        return students;
//    }
//
//    @PutMapping("/student/{id}")
//    public String updateStudent(
//            @PathVariable("id") Integer id,
//            @RequestBody StudentDto studentDto) {
//        return studentService.updateStudent(id, studentDto);
//    }
//
//    @DeleteMapping
//    public String deleteStudent(
//            @PathVariable("id") Integer id) {
//        return studentService.deleteStudent(id);
//    }
//
/// /    @ExceptionHandler(IllegalArgumentException.class)
/// /    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
/// /        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
/// /    }
/// /
/// /    @ExceptionHandler(IllegalArgumentException.class)
/// /    public ResponseEntity<String> UserNotFoundException(Exception ex) {
/// /        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
/// /    }
/// /
/// /    @ExceptionHandler(Exception.class)
/// /    public ResponseEntity<String> handleException(Exception e) {
/// /        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
/// /    }
//
//}
