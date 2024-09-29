package com.project.manager.service;

import com.project.manager.model.Project;
import com.project.manager.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));
    }

    public Project addProject(Project project) {
        if (project.getProjectName() == null || project.getProjectName().isEmpty()) {
            throw new RuntimeException("Project name is required");
        }
        return projectRepository.save(project);
    }

    public Project updateProject(Long projectId, Project project) {
        Project existingProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));

        if (project.getProjectName() != null) {
            existingProject.setProjectName(project.getProjectName());
        }
        if (project.getProjectDescription() != null) {
            existingProject.setProjectDescription(project.getProjectDescription());
        }
        if (project.getProjectStartDate() != null) {
            existingProject.setProjectStartDate(project.getProjectStartDate());
        }
        if (project.getProjectEndDate() != null) {
            existingProject.setProjectEndDate(project.getProjectEndDate());
        }

        return projectRepository.save(existingProject);
    }

    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }


    public List<Project> findProjectWithSorting(String field){
        return projectRepository.findAll(Sort.by(field).descending());
    }

    public Page<Project> findProjectWithPagination(int offset, int pageSize){
        return projectRepository.findAll(PageRequest.of(offset, pageSize));
    }

    public Page<Project> findProjectWithPaginationAndSorting(String field, int offset, int pageSize){
        return projectRepository.findAll(PageRequest.of(offset, pageSize, Sort.by(field).descending()));
    }
}
