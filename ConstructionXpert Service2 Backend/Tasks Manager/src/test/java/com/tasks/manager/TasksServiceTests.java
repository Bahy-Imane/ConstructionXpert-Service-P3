package com.tasks.manager;

import com.tasks.manager.feignClient.Project;
import com.tasks.manager.feignClient.ProjectClient;
import com.tasks.manager.model.Task;
import com.tasks.manager.repository.TaskRepository;
import com.tasks.manager.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class TasksServiceTests {
    
    @Mock
    private TaskRepository taskRepo;

    @Mock
    private ProjectClient projectClient;

    @InjectMocks
    private TaskService taskService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTasksTest() {
        List<Task> expectedTasks = Arrays.asList(new Task(), new Task());
        when(taskRepo.findAll()).thenReturn(expectedTasks);
        List<Task> tasks = taskService.getAllTasks();
        assertEquals(expectedTasks, tasks);
        verify(taskRepo).findAll();

    }

    @Test
    void deleteTaskTest(){
        taskService.deleteTask(1L);
        verify(taskRepo).deleteById(1L);
    }

    @Test
    void getTaskByIdTest(){
        Long id =1L;

        Task task = new Task();
        when(taskRepo.findById(id)).thenReturn(Optional.of(task));

        Task result = taskService.getTaskById(id);
        assertEquals(task, result);
        verify(taskRepo).findById(id);
    }

    @Test
    void TaskUpdateTest(){
        Long id =1L;
        Task existingTask = new Task();
        Task updatedTask = new Task();

        when(taskRepo.findById(id)).thenReturn(Optional.of(existingTask));
        when(taskRepo.save(existingTask)).thenReturn(updatedTask);
        Task result = taskService.updateTask(id, updatedTask);
        assertEquals(updatedTask, result);
        verify(taskRepo).findById(id);
        verify(taskRepo).save(existingTask);
    }

//    @Test
//    void saveTaskTest(){
//        Task task = new Task();
//        Task savedTask = new Task();
//        when(taskRepo.save(task)).thenReturn(savedTask);
//        Task result = taskService.createTask(task);
//        assertEquals(savedTask, result);
//    }

    @Test
    public void testCreateTask_Success() {
        Task task = new Task();
        when(projectClient.getProjectById(1L)).thenReturn(new Project());
        when(taskRepo.save(task)).thenReturn(task);

        Task result = taskService.createTask(task);

        assertNotNull(result);
        assertEquals(task, result);
        verify(taskRepo).save(task);
    }

    @Test
    public void testGetTasksByProjectId_Success() {
        Task task1 = new Task();
        Task task2 = new Task();
        when(taskRepo.getTaskByProjectId(1L)).thenReturn(Arrays.asList(task1, task2));

        List<Task> tasks = taskService.getTasksByProjectId(1L);

        assertNotNull(tasks);
        assertEquals(2, tasks.size());
        assertTrue(tasks.contains(task1));
        assertTrue(tasks.contains(task2));

        verify(taskRepo, times(1)).getTaskByProjectId(1L);
    }
}
