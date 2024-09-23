//package com.ressources.manager;
//
//import com.ressources.manager.feignClient.Task;
//import com.ressources.manager.feignClient.TaskClient;
//import com.ressources.manager.model.Resource;
//import com.ressources.manager.repository.ResourceRepository;
//import com.ressources.manager.service.ResourceService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class ResourceServiceTest {
//
//    @InjectMocks
//    private ResourceService resourceService;
//
//    @Mock
//    private ResourceRepository resourceRepository;
//
//    @Mock
//    private TaskClient taskClient;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testGetAllResources() {
//        List<Resource> resources = Arrays.asList(new Resource(), new Resource());
//        when(resourceRepository.findAll()).thenReturn(resources);
//
//        List<Resource> result = resourceService.getAllResources();
//
//        assertEquals(resources, result);
//        verify(resourceRepository).findAll();
//    }
//
//    @Test
//    void testGetResourceByTaskId() {
//        Long taskId = 1L;
//        List<Resource> resources = Arrays.asList(new Resource(), new Resource());
//        when(resourceRepository.getResourcesByTaskId(taskId)).thenReturn(resources);
//
//        List<Resource> result = resourceService.getResourceByTaskId(taskId);
//
//        assertEquals(resources, result);
//        verify(resourceRepository).getResourcesByTaskId(taskId);
//    }
//
//    @Test
//    void testCreateResource_Success() {
//        Resource resource = new Resource();
//        resource.setTaskId(1L);
//        when(taskClient.getTaskById(1L)).thenReturn(new Task());
//        when(resourceRepository.save(resource)).thenReturn(resource);
//
//        Resource result = resourceService.createResource(resource);
//
//        assertEquals(resource, result);
//        verify(taskClient).getTaskById(1L);
//        verify(resourceRepository).save(resource);
//    }
//
//    @Test
//    void testCreateResource_TaskNotFound() {
//        Resource resource = new Resource();
//        resource.setTaskId(1L);
//        when(taskClient.getTaskById(1L)).thenReturn(null);
//
//        assertThrows(RuntimeException.class, () -> resourceService.createResource(resource));
//        verify(taskClient).getTaskById(1L);
//        verify(resourceRepository, never()).save(any());
//    }
//
//    @Test
//    void testUpdateResource_Success() {
//
//        Long resourceId = 1L;
//        Resource existingResource = new Resource();
//        Resource updatedResource = new Resource();
//        updatedResource.setResourceName("resource");
//        when(resourceRepository.findById(resourceId)).thenReturn(Optional.of(existingResource));
//        when(resourceRepository.save(existingResource)).thenReturn(existingResource);
//
//        Resource result = resourceService.updateResource(resourceId, updatedResource);
//
//        assertEquals("resource", result.getResourceName());
//        verify(resourceRepository).findById(resourceId);
//        verify(resourceRepository).save(existingResource);
//    }
//
//    @Test
//    void testUpdateResource_NotFound() {
//        Long resourceId = 1L;
//        Resource updatedResource = new Resource();
//        when(resourceRepository.findById(resourceId)).thenReturn(Optional.empty());
//
//        assertThrows(RuntimeException.class, () -> resourceService.updateResource(resourceId, updatedResource));
//        verify(resourceRepository).findById(resourceId);
//        verify(resourceRepository, never()).save(any());
//    }
//
//    @Test
//    void testGetResourceById_Found() {
//
//        Long resourceId = 1L;
//        Resource resource = new Resource();
//        when(resourceRepository.findById(resourceId)).thenReturn(Optional.of(resource));
//
//
//        Resource result = resourceService.getResourceById(resourceId);
//
//
//        assertEquals(resource, result);
//        verify(resourceRepository).findById(resourceId);
//    }
//
//    @Test
//    void testGetResourceById_NotFound() {
//
//
//        Long resourceId = 1L;
//        when(resourceRepository.findById(resourceId)).thenReturn(Optional.empty());
//
//
//        Resource result = resourceService.getResourceById(resourceId);
//
//
//        assertNull(result);
//        verify(resourceRepository).findById(resourceId);
//    }
//
//    @Test
//    void testDeleteResource() {
//
//        Long resourceId = 1L;
//
//
//        resourceService.deleteResource(resourceId);
//
//
//        verify(resourceRepository).deleteById(resourceId);
//    }
//}