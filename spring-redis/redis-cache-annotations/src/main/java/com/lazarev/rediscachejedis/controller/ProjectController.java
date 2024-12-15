package com.lazarev.rediscachejedis.controller;

import com.lazarev.rediscachejedis.dto.general.IdResponse;
import com.lazarev.rediscachejedis.dto.project.ProjectDto;
import com.lazarev.rediscachejedis.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<ProjectDto> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Integer id) {
        ProjectDto project = projectService.getProjectById(id);
        return ResponseEntity.ok(project);
    }

    @PostMapping
    public ResponseEntity<IdResponse> createProject(@RequestBody ProjectDto projectDto) {
        IdResponse idResponse = projectService.createProject(projectDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(idResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IdResponse> updateProject(@PathVariable Integer id,
                                                    @RequestBody ProjectDto projectDto) {
        IdResponse idResponse = projectService.updateProject(id, projectDto);
        return ResponseEntity.ok(idResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<IdResponse> patchProject(@PathVariable Integer id,
                                                   @RequestBody ProjectDto projectDto) {
        IdResponse idResponse = projectService.patchProject(id, projectDto);
        return ResponseEntity.ok(idResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<IdResponse> deleteProject(@PathVariable Integer id) {
        IdResponse idResponse = projectService.deleteProject(id);
        return ResponseEntity.ok(idResponse);
    }
}
