package com.tasks.manager.service;

import com.tasks.manager.feignClient.ProjectClient;
import com.tasks.manager.model.Task;
import com.tasks.manager.repository.TaskRepository;
import feign.FeignException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final ProjectClient projectClient;

    public TaskService(TaskRepository taskRepository, ProjectClient projectClient) {
        this.taskRepository = taskRepository;
        this.projectClient = projectClient;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksByProjectId(Long projectId) {
        return taskRepository.getTaskByProjectId(projectId);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow();
    }

    public Task createTask(Task task) {
        try {
            projectClient.getProjectById(task.getProjectId());
            return taskRepository.save(task);
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("Project not found with ID: " + task.getProjectId());
        } catch (FeignException e) {
            throw new RuntimeException("Error communicating with project service", e);
        }
    }

    public Task updateTask(Long id, Task task) {
        Task taskToUpdate = taskRepository.findById(id).orElseThrow();
        taskToUpdate.setTaskTitle(task.getTaskTitle());
        taskToUpdate.setTaskDescription(task.getTaskDescription());
        taskToUpdate.setTaskStatus(task.getTaskStatus());
        return taskRepository.save(taskToUpdate);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getAllTasksWithSorting(String field) {
        return taskRepository.findAll(Sort.by(field).descending());
    }

    public Page<Task> getAllTasksWithPagination(int offset, int pageSize) {
        return taskRepository.findAll(PageRequest.of(offset, pageSize));
    }

}
