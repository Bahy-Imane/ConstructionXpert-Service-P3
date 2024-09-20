package com.ressources.manager.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "tasks-manager")
public interface TaskClient {

    @GetMapping("/api/tasks/{taskId}")
    Task getTaskById(@PathVariable("taskId") Long taskId);

}
