package com.tasks.manager.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "projects-manager")
public interface ProjectClient {

    @GetMapping("/api/projects/{projectId}")
    Project getProjectById(@PathVariable("projectId") Long projectId);
}