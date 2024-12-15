package com.lazarev.springrest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.DETACH;

@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany(cascade = {PERSIST, MERGE, REFRESH, DETACH}, mappedBy = "courses")
    private Set<Student> students = new HashSet<>();

    public void addStudent(Student student) {
        this.students.add(student);
        student.addCourse(this);
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
        students.forEach(s -> s.addCourse(this));
    }
}
