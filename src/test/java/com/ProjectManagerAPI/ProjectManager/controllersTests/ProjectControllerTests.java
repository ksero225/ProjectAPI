package com.ProjectManagerAPI.ProjectManager.controllersTests;

import com.ProjectManagerAPI.ProjectManager.TestDataUtilities;
import com.ProjectManagerAPI.ProjectManager.domain.dto.ProjectDto;
import com.ProjectManagerAPI.ProjectManager.domain.entities.ProjectEntity;
import com.ProjectManagerAPI.ProjectManager.services.ProjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class ProjectControllerTests {

    private final ProjectService projectService;
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    public ProjectControllerTests(ProjectService projectService, MockMvc mockMvc) {
        this.projectService = projectService;
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void testThatCreateProjectSuccessfullyReturnsHttp201Created() throws Exception {
        ProjectEntity projectEntity = TestDataUtilities.createProjectEntityA();
        projectEntity.setProjectId(null);

        String projectJson = objectMapper.writeValueAsString(projectEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(projectJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateProjectSuccessfullyReturnsCreatedProject() throws Exception {
        ProjectDto projectDto = TestDataUtilities.createProjectDtoA();
        projectDto.setProjectId(null);

        String projectDtoJson = objectMapper.writeValueAsString(projectDto);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(projectDtoJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.projectId").value(1L)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.projectTitle").value("Taskify")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.projectDescription").value("Task Management Application")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.projectDueDate").value("2024-01-01")
        );
    }

    @Test
    public void testThatListProjectReturnsHttpStatus200Ok() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatListProjectReturnsProject() throws Exception{
        ProjectEntity projectEntityA = TestDataUtilities.createProjectEntityA();
        projectService.save(projectEntityA);
        ProjectEntity projectEntityB = TestDataUtilities.createProjectEntityB();
        projectService.save(projectEntityB);
        ProjectEntity projectEntityC = TestDataUtilities.createProjectEntityC();
        projectService.save(projectEntityC);


    }
}
