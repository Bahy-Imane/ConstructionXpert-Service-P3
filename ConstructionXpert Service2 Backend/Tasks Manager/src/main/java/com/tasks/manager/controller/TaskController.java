package com.tasks.manager.controller;

import com.tasks.manager.model.Task;
import com.tasks.manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin("*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/project")
    public ResponseEntity<List<Task>> getTasksByProjectId(@RequestParam Long projectId) {
        List<Task> tasks = taskService.getTasksByProjectId(projectId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable("taskId") Long taskId) {
        Task task = taskService.getTaskById(taskId);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task newTask = taskService.createTask(task);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Task> updateTask(@RequestParam Long taskId, @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(taskId, task);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteTask(@RequestParam Long taskId) {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/sorting/{field}")
    public ResponseEntity<List<Task>> getTasksWithSorting(@PathVariable("field") String field) {
        List<Task> tasks = taskService.getAllTasksWithSorting(field);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public ResponseEntity<Page<Task>> getTasksWithPagination(@PathVariable("offset") int offset,@PathVariable("pageSize") int pageSize) {
        Page<Task> tasks = taskService.getAllTasksWithPagination(offset, pageSize);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/paginationAndSorting/{offset}/{pageSize}/{field}")
    public ResponseEntity<Page<Task>> getTasksWithPaginationAndSoring(@PathVariable("offset") int offset,@PathVariable("pageSize") int pageSize,@PathVariable("field") String field) {
        Page<Task> tasks = taskService.getAllTasksWithPaginationAndSorting( offset,  pageSize, field);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
}
