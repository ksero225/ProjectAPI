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

import java.util.HashSet;
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
        //Create WorkerEntity, save it, and check if its present and equal to saved entity.
        WorkerEntity testWorkerEntity = TestDataUtilities.createTestWorkerEntityA();
        underTestWorkerRepository.save(testWorkerEntity);
        Optional<WorkerEntity> result = underTestWorkerRepository.findById(testWorkerEntity.getWorkerId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(testWorkerEntity);
    }

    @Test
    public void testThatMultipleWorkersCanBeCreatedAndRecalled() {
        //Create 3 WorkerEntities, save all of them, then check if size is 3 and contains exactly what was saved in any order.
        WorkerEntity testWorkerEntityA = TestDataUtilities.createTestWorkerEntityA();
        underTestWorkerRepository.save(testWorkerEntityA);
        WorkerEntity testWorkerEntityB = TestDataUtilities.createTestWorkerEntityB();
        underTestWorkerRepository.save(testWorkerEntityB);
        WorkerEntity testWorkerEntityC = TestDataUtilities.createTestWorkerEntityC();
        underTestWorkerRepository.save(testWorkerEntityC);

        Iterable<WorkerEntity> result = underTestWorkerRepository.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        testWorkerEntityA,
                        testWorkerEntityB,
                        testWorkerEntityC
                );

    }

    @Test
    public void testThatWorkerCanBeUpdated() {
        //Create WorkerEntity, save it, update it, save again, then check that its saved, and it's exactly the same as updated entity.
        WorkerEntity testWorkerEntityA = TestDataUtilities.createTestWorkerEntityA();
        underTestWorkerRepository.save(testWorkerEntityA);

        testWorkerEntityA.setWorkerName("UPDATED");

        underTestWorkerRepository.save(testWorkerEntityA);

        Optional<WorkerEntity> result = underTestWorkerRepository.findById(testWorkerEntityA.getWorkerId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(testWorkerEntityA);
    }

    @Test
    public void testThatWorkerCanBeDeleted() {
        //Create WorkerEntity, save it, then check that its saved, then delete it, and check if it's not present anymore.
        WorkerEntity testWorkerEntityA = TestDataUtilities.createTestWorkerEntityA();
        underTestWorkerRepository.save(testWorkerEntityA);

        Optional<WorkerEntity> result = underTestWorkerRepository.findById(testWorkerEntityA.getWorkerId());
        assertThat(result).isPresent();

        underTestWorkerRepository.deleteById(testWorkerEntityA.getWorkerId());
        result = underTestWorkerRepository.findById(testWorkerEntityA.getWorkerId());

        assertThat(result).isEmpty();
    }

    @Test
    public void testThatWorkerProjectHashSetIsEmpty() {
        //Create WorkerEntity, set its HashSet to empty (not null), then check if Entity is empty and present.
        WorkerEntity testWorkerEntityA = TestDataUtilities.createTestWorkerEntityA();
        testWorkerEntityA.setProjectsAssignedToThisWorker(new HashSet<>());
        underTestWorkerRepository.save(testWorkerEntityA);

        Optional<WorkerEntity> result = underTestWorkerRepository.findById(testWorkerEntityA.getWorkerId());

        assertThat(result).isPresent();
        assertThat(result.get().getProjectsAssignedToThisWorker()).isEmpty();
    }

}
