package com.tasks.manager.service;

import com.tasks.manager.feignClient.ProjectClient;
import com.tasks.manager.model.Task;
import com.tasks.manager.repository.TaskRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectClient projectClient;

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
}
