package com.lazarev.springrest.mapper;

import com.lazarev.springrest.dto.CourseDto;
import com.lazarev.springrest.dto.StudentDto;
import com.lazarev.springrest.entity.Course;
import com.lazarev.springrest.entity.Student;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentCourseMapper {
    Student toStudent(StudentDto studentDto);

    StudentDto toStudentDto(Student student);

    List<StudentDto> toStudentDtos(List<Student> students);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "courses", ignore = true)
    void updateStudent(@MappingTarget Student student, StudentDto studentDto);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchStudent(@MappingTarget Student student, StudentDto studentDto);

    Course toCourse(CourseDto courseDto);
}
