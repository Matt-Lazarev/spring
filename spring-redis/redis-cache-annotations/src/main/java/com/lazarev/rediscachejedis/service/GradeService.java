package com.lazarev.rediscachejedis.service;

import com.lazarev.rediscachejedis.dto.general.IdResponse;
import com.lazarev.rediscachejedis.dto.grade.GradeDto;
import com.lazarev.rediscachejedis.entity.Grade;
import com.lazarev.rediscachejedis.enums.Operation;
import com.lazarev.rediscachejedis.repository.GradeRepository;
import com.lazarev.rediscachejedis.service.mapper.GradeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static com.lazarev.rediscachejedis.statics.StaticConstants.Fields.*;

@Service
@RequiredArgsConstructor
public class GradeService {
    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;

    public List<GradeDto> getAllGrades() {
        List<Grade> grades = gradeRepository.findAll();
        return gradeMapper.toDtoList(grades);
    }

    public GradeDto getGradeById(Integer id) {
        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(GRADE_NOT_FOUND.formatted(id)));
        return gradeMapper.toDto(grade);
    }

    public Grade getGradeReferenceById(Integer id){
        if(!gradeRepository.existsById(id)){
            throw new NoSuchElementException(GRADE_NOT_FOUND.formatted(id));
        }
        return gradeRepository.getReferenceById(id);
    }

    @Transactional
    public IdResponse createGrade(GradeDto gradeDto) {
        Grade grade = gradeMapper.toEntity(gradeDto);
        Grade savedGrade = gradeRepository.save(grade);

        return new IdResponse(savedGrade.getId(), Operation.SAVE);
    }

    @Transactional
    public IdResponse updateGrade(Integer id, GradeDto gradeDto) {
        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(GRADE_NOT_FOUND.formatted(id)));

        gradeMapper.update(grade, gradeDto);

        return new IdResponse(id, Operation.UPDATE);
    }

    @Transactional
    public IdResponse patchGrade(Integer id, GradeDto gradeDto) {
        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(GRADE_NOT_FOUND.formatted(id)));

        gradeMapper.patch(grade, gradeDto);

        return new IdResponse(id, Operation.PATCH);
    }

    @Transactional
    public IdResponse deleteGrade(Integer id) {
        int rowsDeleted = gradeRepository.deleteGradeById(id);
        if(rowsDeleted != 1){
            throw new NoSuchElementException(GRADE_NOT_FOUND.formatted(id));
        }
        return new IdResponse(id, Operation.DELETE);
    }
}
