package com.lazarev.springrest.repository;

import com.lazarev.springrest.entity.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("select s from Student s left join fetch s.courses")
    List<Student> findAllStudents();

    @Query("select s from Student s left join fetch s.courses where s.id = :id")
    Optional<Student> findStudentById(Integer id);

    @Modifying
    @Query("delete from Student s where s.id = :id")
    int deleteStudentById(Integer id);

    @Modifying
    @Query(value = """
            insert into students_courses (student_id, course_id)
            select :studentId, :courseId
            where exists (select s.id from students s where s.id = :studentId)
          """,
            nativeQuery = true)
    int addCourseToStudent(Integer studentId, Integer courseId);

    @Query(value = """
            select case when count(*) > 0 then true else false end
            from students_courses sc
            where sc.student_id = :studentId and sc.course_id = :courseId;
          """,
            nativeQuery = true)
    boolean existsStudentCourseRow(Integer studentId, Integer courseId);

    @Modifying
    @Query(value = """
            insert into students_courses (student_id, course_id)
            select :studentId, :courseId
            where   exists (select s.id from students s where s.id = :studentId)
            and     exists (select c.id from courses c where c.id = :courseId)
          """,
            nativeQuery = true)
    int attachCourseToStudent(Integer studentId, Integer courseId);

    @Modifying
    @Query(value = """
            delete from students_courses sc
            where sc.student_id = :studentId
            and   sc.course_id = :courseId
          """,
            nativeQuery = true)
    int detachCourseToStudent(Integer studentId, Integer courseId);
}
