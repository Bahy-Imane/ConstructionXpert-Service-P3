package com.ressources.manager.service;

import com.ressources.manager.feignClient.TaskClient;
import com.ressources.manager.model.Resource;
import com.ressources.manager.repository.ResourceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;

    private final TaskClient taskClient;

    private Sort.Direction sortDirection;

    public ResourceService(ResourceRepository resourceRepository, TaskClient taskClient) {
        this.resourceRepository = resourceRepository;
        this.taskClient = taskClient;
    }

    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    public List<Resource> getResourceByTaskId(Long taskId) {
        return resourceRepository.getResourcesByTaskId(taskId);
    }

    public Resource createResource(Resource resource) {
        if (taskClient.getTaskById(resource.getTaskId()) != null) {
            return resourceRepository.save(resource);
        }
        throw new RuntimeException("Task not found");
    }

    public Resource updateResource(Long resourceId, Resource resource) {
        Resource existingResource = resourceRepository.findById(resourceId).orElseThrow(() -> new RuntimeException("Resource not found"));
        existingResource.setResourceName(resource.getResourceName());
        existingResource.setResourceType(resource.getResourceType());
        existingResource.setResourceQuantity(resource.getResourceQuantity());
        existingResource.setResourceProvider(resource.getResourceProvider());
        return resourceRepository.save(existingResource);
    }

    public Resource getResourceById(Long resourceId) {
        return resourceRepository.findById(resourceId).orElse(null);
    }
    public void deleteResource(Long resourceId) {
        resourceRepository.deleteById(resourceId);
    }

    public List<Resource> findResourcesWithSorting(String field , String direction){
        try {
            sortDirection = Sort.Direction.valueOf(direction.toUpperCase());
        } catch (IllegalArgumentException e) {
            sortDirection = Sort.Direction.ASC;
        }
        return resourceRepository.findAll(Sort.by(sortDirection,field));
    }

    public Page<Resource> findResourcesWithPagination(int offset, int pageSize){
        return resourceRepository.findAll(PageRequest.of(offset, pageSize));
    }

    public Page<Resource> findResourcesWithSortingAndPagination(String field, int offset, int pageSize){
        return resourceRepository.findAll(PageRequest.of(offset, pageSize, Sort.by(field).descending()));
    }
}
