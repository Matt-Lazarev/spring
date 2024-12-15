package com.lazarev.springrest.controller;

import com.lazarev.springrest.dto.CourseDto;
import com.lazarev.springrest.dto.StudentDto;
import com.lazarev.springrest.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Integer id) {
        StudentDto studentDto = studentService.getStudentById(id);
        return ResponseEntity.ok(studentDto);
    }

    @PostMapping
    public ResponseEntity<StudentDto> saveStudent(@RequestBody StudentDto studentDto) {
        studentService.saveStudent(studentDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentDto> patchStudent(@PathVariable Integer id,
                                                   @RequestBody StudentDto studentDto) {
        studentService.patchStudent(id, studentDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Integer id,
                                                    @RequestBody StudentDto studentDto) {
        studentService.updateStudent(id, studentDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/courses")
    public ResponseEntity<StudentDto> addNewCourseToStudent(@PathVariable Integer id,
                                                            @RequestBody CourseDto courseDto) {
        studentService.addNewCourseToStudent(id, courseDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{studentId}/courses/{courseId}")
    public ResponseEntity<StudentDto> attachCourseToStudent(@PathVariable Integer studentId,
                                                            @PathVariable Integer courseId) {
        studentService.attachCourseToStudent(studentId, courseId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{studentId}/courses/{courseId}")
    public ResponseEntity<Void> detachCourseFromStudent(@PathVariable Integer studentId,
                                                        @PathVariable Integer courseId) {
        studentService.detachCourseFromStudent(studentId, courseId);
        return ResponseEntity.ok().build();
    }
}
