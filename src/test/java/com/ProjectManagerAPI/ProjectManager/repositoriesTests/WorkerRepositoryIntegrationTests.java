package com.ProjectManagerAPI.ProjectManager.repositoriesTests;

import com.ProjectManagerAPI.ProjectManager.TestDataUtilities;
import com.ProjectManagerAPI.ProjectManager.domain.entities.WorkerEntity;
import com.ProjectManagerAPI.ProjectManager.repositories.WorkerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class WorkerRepositoryIntegrationTests {

    private final WorkerRepository underTestWorkerRepository;

    @Autowired
    public WorkerRepositoryIntegrationTests(WorkerRepository underTestWorkerRepository) {
        this.underTestWorkerRepository = underTestWorkerRepository;
    }

    @Test
    public void testThatWorkerCanBeCreatedAndRecalled() {
        WorkerEntity testWorkerEntity = TestDataUtilities.createTestWorkerEntityA();
        underTestWorkerRepository.save(testWorkerEntity);
        Optional<WorkerEntity> result = underTestWorkerRepository.findById(testWorkerEntity.getWorkerId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(testWorkerEntity);
    }

    @Test
    public void testThatMultipleWorkersCanBeCreatedAndRecalled() {
        WorkerEntity testWorkerEntityA = TestDataUtilities.createTestWorkerEntityA();
        underTestWorkerRepository.save(testWorkerEntityA);
        WorkerEntity testWorkerEntityB = TestDataUtilities.createTestWorkerEntityB();
        underTestWorkerRepository.save(testWorkerEntityB);
        WorkerEntity testWorkerEntityC = TestDataUtilities.createTestWorkerEntityC();
        underTestWorkerRepository.save(testWorkerEntityC);

        Iterable<WorkerEntity> result = underTestWorkerRepository.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactlyInAnyOrder(testWorkerEntityA, testWorkerEntityB, testWorkerEntityC);

    }
}
