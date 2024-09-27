//package com.project.manager.service;
//
//import com.project.manager.model.Project;
//import com.project.manager.repository.ProjectRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.*;
//
//class ProjectServiceTest {
//
//    @Mock
//    private ProjectRepository projectRepository;
//
//    @InjectMocks
//    private ProjectService projectService;
//
//    private Project project;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        project = new Project();
//        project.setProjectId(1L);
//        project.setProjectName("Test Project");
//        project.setProjectDescription("This is a test project");
//        project.setProjectStartDate(LocalDate.of(2024, 1, 1));
//        project.setProjectEndDate(LocalDate.of(2024, 12, 31));
//    }
//
//    @Test
//    void getAllProjects_ShouldReturnProjectList() {
//        when(projectRepository.findAll()).thenReturn(Arrays.asList(project));
//
//        List<Project> result = projectService.getAllProjects();
//
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        assertEquals("Test Project", result.get(0).getProjectName());
//        verify(projectRepository, times(1)).findAll();
//    }
//
//    @Test
//    void getProjectById_ShouldReturnProject() {
//        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
//
//        Project result = projectService.getProjectById(1L);
//
//        assertNotNull(result);
//        assertEquals(1L, result.getProjectId());
//        assertEquals("Test Project", result.getProjectName());
//        verify(projectRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    void getProjectById_ShouldThrowException_WhenProjectNotFound() {
//        when(projectRepository.findById(1L)).thenReturn(Optional.empty());
//
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            projectService.getProjectById(1L);
//        });
//
//        assertEquals("Project not found with id: 1", exception.getMessage());
//        verify(projectRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    void addProject_ShouldSaveAndReturnProject() {
//        when(projectRepository.save(any(Project.class))).thenReturn(project);
//
//        Project result = projectService.addProject(project);
//
//        assertNotNull(result);
//        assertEquals("Test Project", result.getProjectName());
//        verify(projectRepository, times(1)).save(any(Project.class));
//    }
//
//    @Test
//    void addProject_ShouldThrowException_WhenProjectNameIsEmpty() {
//        project.setProjectName("");
//
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            projectService.addProject(project);
//        });
//
//        assertEquals("Project name is required", exception.getMessage());
//        verify(projectRepository, times(0)).save(any(Project.class));
//    }
//
//    @Test
//    void updateProject_ShouldUpdateAndReturnProject() {
//        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
//        when(projectRepository.save(any(Project.class))).thenReturn(project);
//
//        Project updatedProject = new Project();
//        updatedProject.setProjectName("Updated Project");
//
//        Project result = projectService.updateProject(1L, updatedProject);
//
//        assertNotNull(result);
//        assertEquals("Updated Project", result.getProjectName());
//        verify(projectRepository, times(1)).findById(1L);
//        verify(projectRepository, times(1)).save(any(Project.class));
//    }
//
//    @Test
//    void updateProject_ShouldThrowException_WhenProjectNotFound() {
//        when(projectRepository.findById(1L)).thenReturn(Optional.empty());
//
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            projectService.updateProject(1L, project);
//        });
//
//        assertEquals("Project not found with id: 1", exception.getMessage());
//        verify(projectRepository, times(1)).findById(1L);
//        verify(projectRepository, times(0)).save(any(Project.class));
//    }
//
//    @Test
//    void deleteProject_ShouldDeleteProject() {
//        doNothing().when(projectRepository).deleteById(1L);
//
//        projectService.deleteProject(1L);
//
//        verify(projectRepository, times(1)).deleteById(1L);
//    }
//}
