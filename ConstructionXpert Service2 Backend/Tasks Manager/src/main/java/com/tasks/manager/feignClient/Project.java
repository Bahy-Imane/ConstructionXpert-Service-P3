package com.tasks.manager.feignClient;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Project {

    private Long projectId;
    private String projectName;
    private String projectDescription;
    private LocalDate projectStartDate;
    private LocalDate projectEndDate;
}
