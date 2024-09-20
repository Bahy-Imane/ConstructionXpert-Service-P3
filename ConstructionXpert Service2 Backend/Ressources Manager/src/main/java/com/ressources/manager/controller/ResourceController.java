package com.ressources.manager.controller;

import com.ressources.manager.model.Resource;
import com.ressources.manager.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
@CrossOrigin("*")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping("/all")
    public ResponseEntity<List<Resource>> getAllResources() {
        List<Resource> resources = resourceService.getAllResources();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }
    @GetMapping("{resourceId}")
    public Resource getResource(@PathVariable("resourceId") Long resourceId) {
        return resourceService.getResourceById(resourceId);
    }

    @GetMapping("/task")
    public ResponseEntity<List<Resource>> getResourceByTaskId(@RequestParam Long taskId) {
        List<Resource> resources = resourceService.getResourceByTaskId(taskId);
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Resource> createResource(@RequestBody Resource resource) {
        Resource newResource = resourceService.createResource(resource);
        return new ResponseEntity<>(newResource, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Resource> updateResource(@RequestParam Long resourceId, @RequestBody Resource resource) {
        Resource updatedResource = resourceService.updateResource(resourceId, resource);
        return new ResponseEntity<>(updatedResource, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteResource(@RequestParam Long resourceId) {
        resourceService.deleteResource(resourceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
