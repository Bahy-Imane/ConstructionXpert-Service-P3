package com.project.manager.controller;

import com.project.manager.model.Project;
import com.project.manager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin("*")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/all")
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable("projectId") Long projectId) {
        Project project = projectService.getProjectById(projectId);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PostMapping("/add-project")
    public ResponseEntity<Project> addProject(@RequestBody Project project) {
        Project newProject = projectService.addProject(project);
        return new ResponseEntity<>(newProject, HttpStatus.CREATED);
    }

    @PutMapping("/update-project")
    public ResponseEntity<Project> updateProject(@RequestParam Long projectId, @RequestBody Project project) {
        Project updatedProject = projectService.updateProject(projectId, project);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    @DeleteMapping("/delete-project")
    public ResponseEntity<Void> deleteProject(@RequestParam Long projectId) {
        projectService.deleteProject(projectId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/sorting/{field}")
    public ResponseEntity<List<Project>> getAllWithSorting(@PathVariable String field) {
        List<Project> projects = projectService.findProjectsWithSorting(field);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
    @GetMapping("/pagination/{offset}/{pageSize}")
    public ResponseEntity<Page<Project>> getAllWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Project> projects = projectService.findProjectsWithPagination(offset, pageSize);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/paginationAndSorting/{offset}/{pageSize}/{field}")
    public ResponseEntity<Page<Project>> getAllWithPaginationAndSorting(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
        Page<Project> projects = projectService.findProjectsWithPaginationAndSorting(offset, pageSize,field);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
}
