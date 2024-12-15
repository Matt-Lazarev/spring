package com.lazarev.springrest.service;

import com.lazarev.springrest.dto.CourseDto;
import com.lazarev.springrest.dto.StudentDto;
import com.lazarev.springrest.entity.Student;
import com.lazarev.springrest.exception.ResourceAlreadyExistsException;
import com.lazarev.springrest.exception.ResourceNotFoundException;
import com.lazarev.springrest.mapper.StudentCourseMapper;
import com.lazarev.springrest.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentCourseMapper studentCourseMapper;
    private final CourseService courseService;

    @Transactional(readOnly = true)
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAllStudents();
        return studentCourseMapper.toStudentDtos(students);
    }

    @Transactional(readOnly = true)
    public StudentDto getStudentById(Integer id) {
        Student student = findStudentById(id, true);
        return studentCourseMapper.toStudentDto(student);
    }

    @Transactional
    public void saveStudent(StudentDto studentDto) {
        Student student = studentCourseMapper.toStudent(studentDto);
        studentRepository.save(student);
    }

    @Transactional
    public void updateStudent(Integer id, StudentDto studentDto) {
        Student student = findStudentById(id, false);
        studentCourseMapper.updateStudent(student, studentDto);
        studentRepository.save(student);
    }

    @Transactional
    public void patchStudent(Integer id, StudentDto studentDto) {
        Student student = findStudentById(id, false);
        studentCourseMapper.patchStudent(student, studentDto);
        studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(Integer id) {
        if (studentRepository.deleteStudentById(id) == 0) {
            throw studentNotFoundException(id);
        }
    }

    @Transactional
    public void addNewCourseToStudent(Integer studentId, CourseDto courseDto) {
        Integer courseId = courseService.saveCourse(courseDto);

        if (studentRepository.addCourseToStudent(studentId, courseId) == 0) {
            throw studentNotFoundException(studentId);
        }
    }

    @Transactional
    public void attachCourseToStudent(Integer studentId, Integer courseId) {
        if(studentRepository.existsStudentCourseRow(studentId, courseId)) {
            throw studentCourseRowAlreadyExistsException(studentId, courseId);
        }
        if (studentRepository.attachCourseToStudent(studentId, courseId) == 0) {
            throw studentOrCourseNotFoundException(studentId, courseId);
        }
    }

    @Transactional
    public void detachCourseFromStudent(Integer studentId, Integer courseId) {
        if (studentRepository.detachCourseToStudent(studentId, courseId) == 0) {
            throw studentOrCourseNotFoundException(studentId, courseId);
        }
    }

    private Student findStudentById(Integer id, boolean fetchCourses) {
        Optional<Student> student = fetchCourses
                ? studentRepository.findStudentById(id)
                : studentRepository.findById(id);
        return student.orElseThrow(() -> studentNotFoundException(id));
    }

    private ResourceNotFoundException studentNotFoundException(Integer studentId) {
        return new ResourceNotFoundException(
                "Student with id = '%d' not found".formatted(studentId)
        );
    }

    private ResourceNotFoundException studentOrCourseNotFoundException(Integer studentId, Integer courseId) {
        return new ResourceNotFoundException(
                "Student with id = '%d' or course with id = '%d' not found".formatted(studentId, courseId)
        );
    }

    private ResourceAlreadyExistsException studentCourseRowAlreadyExistsException(Integer studentId, Integer courseId) {
        return new ResourceAlreadyExistsException(
                "Student with id = '%d' is already added to course with id = '%d'".formatted(studentId, courseId)
        );
    }
}
