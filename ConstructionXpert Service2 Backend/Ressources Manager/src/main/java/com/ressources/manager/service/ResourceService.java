package com.ressources.manager.service;

import com.ressources.manager.feignClient.TaskClient;
import com.ressources.manager.model.Resource;
import com.ressources.manager.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private TaskClient taskClient;

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
        throw new RuntimeException("Tâche non trouvée");
    }

    public Resource updateResource(Long resourceId, Resource resource) {
        Resource existingResource = resourceRepository.findById(resourceId).orElseThrow(() -> new RuntimeException("Ressource non trouvée"));
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
}
