package com.lazarev.rediscachejedis.controller;

import com.lazarev.rediscachejedis.dto.general.IdResponse;
import com.lazarev.rediscachejedis.dto.grade.GradeDto;
import com.lazarev.rediscachejedis.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
@RequiredArgsConstructor
public class GradeController {
    private final GradeService gradeService;

    @GetMapping
    public ResponseEntity<List<GradeDto>> getAllGrades() {
        List<GradeDto> grades = gradeService.getAllGrades();
        return ResponseEntity.ok(grades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GradeDto> getGradeById(@PathVariable Integer id) {
        GradeDto grade = gradeService.getGradeById(id);
        return ResponseEntity.ok(grade);
    }

    @PostMapping
    public ResponseEntity<IdResponse> createGrade(@RequestBody GradeDto gradeDto) {
        IdResponse idResponse = gradeService.createGrade(gradeDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(idResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IdResponse> updateGrade(@PathVariable Integer id,
                                                  @RequestBody GradeDto gradeDto) {
        IdResponse idResponse = gradeService.updateGrade(id, gradeDto);
        return ResponseEntity.ok(idResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<IdResponse> patchGrade(@PathVariable Integer id,
                                                    @RequestBody GradeDto gradeDto) {
        IdResponse idResponse = gradeService.patchGrade(id, gradeDto);
        return ResponseEntity.ok(idResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGrade(@PathVariable Integer id) {
        IdResponse idResponse = gradeService.deleteGrade(id);
        return ResponseEntity.ok(idResponse);
    }
}
