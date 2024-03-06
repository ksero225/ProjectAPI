package com.ProjectManagerAPI.ProjectManager.controllersTests;

import com.ProjectManagerAPI.ProjectManager.TestDataUtilities;
import com.ProjectManagerAPI.ProjectManager.domain.dto.WorkerDto;
import com.ProjectManagerAPI.ProjectManager.domain.entities.ProjectEntity;
import com.ProjectManagerAPI.ProjectManager.domain.entities.WorkerEntity;
import com.ProjectManagerAPI.ProjectManager.services.WorkerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Set;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class WorkerControllerTests {

    private final WorkerService workerService;
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    public WorkerControllerTests(WorkerService workerService, MockMvc mockMvc) {
        this.workerService = workerService;
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreateWorkerSuccessfullyReturnsHttp201Created() throws Exception {
        WorkerEntity workerEntity = TestDataUtilities.createTestWorkerEntityA();
        workerEntity.setWorkerId(null);

        String workerJson = objectMapper.writeValueAsString(workerEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/workers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(workerJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateWorkerSuccessfullyReturnsSavedWorker() throws Exception{
        WorkerDto workerDto = TestDataUtilities.createTestWorkerDtoA();
        workerDto.setWorkerId(null);

        String workerJson = objectMapper.writeValueAsString(workerDto);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/workers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(workerJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.workerId").value(1L)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.workerName").value("Pawel")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.workerSurname").value("Nowak")
        );
    }

    @Test
    public void testThatListWorkersReturnsHttpStatus200Ok() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/workers")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatListWorkersReturnsWorker() throws Exception{
        WorkerEntity workerEntity = TestDataUtilities.createTestWorkerEntityA();
        workerEntity.setWorkerId(null);

        workerService.save(workerEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/workers")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].workerId").value(1L)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].workerName").value("Pawel")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].workerSurname").value("Nowak")
        );

    }
}
