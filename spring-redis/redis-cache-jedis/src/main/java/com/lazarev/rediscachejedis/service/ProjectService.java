package com.lazarev.rediscachejedis.service;

import com.lazarev.rediscachejedis.dto.general.IdResponse;
import com.lazarev.rediscachejedis.dto.project.ProjectDto;
import com.lazarev.rediscachejedis.entity.Project;
import com.lazarev.rediscachejedis.enums.Operation;
import com.lazarev.rediscachejedis.repository.ProjectRepository;
import com.lazarev.rediscachejedis.service.mapper.ProjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static com.lazarev.rediscachejedis.statics.StaticConstants.Fields.PROJECT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public List<ProjectDto> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projectMapper.toDtoList(projects);
    }

    public ProjectDto getProjectById(Integer id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(PROJECT_NOT_FOUND.formatted(id)));
        return projectMapper.toDto(project);
    }

    public Project getProjectReferenceById(Integer id) {
        if(!projectRepository.existsById(id)){
            throw new NoSuchElementException(PROJECT_NOT_FOUND.formatted(id));
        }
        return projectRepository.getReferenceById(id);
    }

    @Transactional
    public IdResponse createProject(ProjectDto projectDto) {
        Project project = projectMapper.toEntity(projectDto);
        Project savedProject = projectRepository.save(project);
        return new IdResponse(savedProject.getId(), Operation.SAVE);
    }

    @Transactional
    public IdResponse updateProject(Integer id, ProjectDto projectDto) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(PROJECT_NOT_FOUND.formatted(id)));

        projectMapper.update(project, projectDto);

        return new IdResponse(id, Operation.UPDATE);
    }

    @Transactional
    public IdResponse patchProject(Integer id, ProjectDto projectDto) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(PROJECT_NOT_FOUND.formatted(id)));

        projectMapper.patch(project, projectDto);

        return new IdResponse(id, Operation.UPDATE);
    }

    public IdResponse deleteProject(Integer id) {
        if(!projectRepository.existsById(id)){
            throw new NoSuchElementException(PROJECT_NOT_FOUND.formatted(id));
        }
        projectRepository.deleteById(id);
        return new IdResponse(id, Operation.DELETE);
    }
}
