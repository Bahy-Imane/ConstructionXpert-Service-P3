package com.project.manager;

import com.project.manager.controller.ProjectController;
import com.project.manager.model.Project;
import com.project.manager.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectControllerTest {

    @InjectMocks
    private ProjectController projectController;

    @Mock
    private ProjectService projectService;

    private Project project;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        project = new Project();
        project.setProjectId(1L);
        project.setProjectName("Test Project");
    }

    @Test
    void getAllProjects_ShouldReturnListOfProjects() {
        List<Project> projects = Arrays.asList(project);
        when(projectService.getAllProjects()).thenReturn(projects);

        ResponseEntity<List<Project>> response = projectController.getAllProjects();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(projects, response.getBody());
        verify(projectService, times(1)).getAllProjects();
    }

    @Test
    void getProjectById_ProjectExists_ShouldReturnProject() {
        when(projectService.getProjectById(1L)).thenReturn(project);

        ResponseEntity<Project> response = projectController.getProjectById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(project, response.getBody());
        verify(projectService, times(1)).getProjectById(1L);
    }

    @Test
    void getProjectById_ProjectNotFound_ShouldReturnNotFound() {
        when(projectService.getProjectById(1L)).thenReturn(null);

        ResponseEntity<Project> response = projectController.getProjectById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
        verify(projectService, times(1)).getProjectById(1L);
    }

    @Test
    void addProject_ShouldReturnNewProject() {
        when(projectService.addProject(any(Project.class))).thenReturn(project);

        ResponseEntity<Project> response = projectController.addProject(project);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(project, response.getBody());
        verify(projectService, times(1)).addProject(project);
    }

    @Test
    void updateProject_ShouldReturnUpdatedProject() {
        when(projectService.updateProject(anyLong(), any(Project.class))).thenReturn(project);

        ResponseEntity<Project> response = projectController.updateProject(1L, project);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(project, response.getBody());
        verify(projectService, times(1)).updateProject(1L, project);
    }

    @Test
    void deleteProject_ShouldReturnNoContent() {
        doNothing().when(projectService).deleteProject(1L);

        ResponseEntity<Void> response = projectController.deleteProject(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(projectService, times(1)).deleteProject(1L);
    }
}
