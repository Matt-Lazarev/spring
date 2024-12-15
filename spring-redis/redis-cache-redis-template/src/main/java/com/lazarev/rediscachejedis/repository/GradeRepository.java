package com.lazarev.rediscachejedis.repository;

import com.lazarev.rediscachejedis.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
    @Modifying
    @Query("delete from Grade g where g.id = :id")
    int deleteGradeById(Integer id);
}
