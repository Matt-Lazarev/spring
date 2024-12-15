package com.lazarev.springrest.service;

import com.lazarev.springrest.dto.CourseDto;
import com.lazarev.springrest.entity.Course;
import com.lazarev.springrest.mapper.StudentCourseMapper;
import com.lazarev.springrest.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final StudentCourseMapper studentCourseMapper;

    @Transactional
    public Integer saveCourse(CourseDto courseDto) {
        Course course = studentCourseMapper.toCourse(courseDto);
        return courseRepository.save(course).getId();
    }
}
