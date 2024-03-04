package com.ProjectManagerAPI.ProjectManager.repositoriesTests;

import com.ProjectManagerAPI.ProjectManager.TestDataUtilities;
import com.ProjectManagerAPI.ProjectManager.domain.entities.ProjectEntity;
import com.ProjectManagerAPI.ProjectManager.repositories.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProjectRepositoryIntegrationTests {

    private final ProjectRepository underTestsProjectRepository;

    @Autowired
    public ProjectRepositoryIntegrationTests(ProjectRepository projectRepository) {
        this.underTestsProjectRepository = projectRepository;
    }

    @Test
    public void testThatProjectCanBeCreatedAndRecalled() {
        //Create ProjectEntity, save it, and check if its present and equal to saved entity.
        ProjectEntity testProjectEntity = TestDataUtilities.createProjectEntityA();
        underTestsProjectRepository.save(testProjectEntity);

        Optional<ProjectEntity> result = underTestsProjectRepository.findById(testProjectEntity.getProjectId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(testProjectEntity);
    }

    @Test
    void testThatMultipleProjectsCanBeCreatedAndRecalled() {
        //Create 3 ProjectEntities, save all of them, then check if size is 3 and contains exactly what was saved in any order.
        ProjectEntity testProjectEntityA = TestDataUtilities.createProjectEntityA();
        underTestsProjectRepository.save(testProjectEntityA);
        ProjectEntity testProjectEntityB = TestDataUtilities.createProjectEntityB();
        underTestsProjectRepository.save(testProjectEntityB);
        ProjectEntity testProjectEntityC = TestDataUtilities.createProjectEntityC();
        underTestsProjectRepository.save(testProjectEntityC);

        Iterable<ProjectEntity> result = underTestsProjectRepository.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        testProjectEntityA,
                        testProjectEntityB,
                        testProjectEntityC
                );
    }

    @Test
    public void testThatProjectCanBeUpdated() {
        //Create ProjectEntity, save it, update it, save again, then check that its saved, and it's exactly the same as updated entity.
        ProjectEntity testProjectEntity = TestDataUtilities.createProjectEntityA();
        underTestsProjectRepository.save(testProjectEntity);

        testProjectEntity.setProjectTitle("UPDATED");
        underTestsProjectRepository.save(testProjectEntity);

        Optional<ProjectEntity> result = underTestsProjectRepository.findById(testProjectEntity.getProjectId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(testProjectEntity);
    }

    @Test
    public void testThatProjectCanBeDeleted(){
        //Create ProjectEntity, save it, then check that its saved, then delete it, and check if it's not present anymore.
        ProjectEntity testProjectEntity = TestDataUtilities.createProjectEntityA();
        underTestsProjectRepository.save(testProjectEntity);

        Optional<ProjectEntity> result = underTestsProjectRepository.findById(testProjectEntity.getProjectId());
        assertThat(result)
                .isPresent()
                .isNotEmpty();

        underTestsProjectRepository.deleteById(testProjectEntity.getProjectId());

        result = underTestsProjectRepository.findById(testProjectEntity.getProjectId());
        assertThat(result).isEmpty();
    }

    //This tests below probably should be in controller tests (move it later)

    @Test
    public void testThatProjectWorkersHashSetIsEmpty(){
        //Create ProjectEntity, set its HashSet to empty (not null), then check if Entity is empty and present.
        ProjectEntity testProjectEntity = TestDataUtilities.createProjectEntityA();
        testProjectEntity.setWorkersAssignedToThisProject(new HashSet<>());
        underTestsProjectRepository.save(testProjectEntity);

        Optional<ProjectEntity> result = underTestsProjectRepository.findById(testProjectEntity.getProjectId());
        assertThat(result).isPresent();
        assertThat(result.get().getWorkersAssignedToThisProject()).isEmpty();
    }

    @Test
    public void testThatProjectIsOutOfDate(){
        //Create ProjectEntity, save it, get its dueDate and check if it's before today.
        ProjectEntity testProjectEntity = TestDataUtilities.createProjectEntityA();
        underTestsProjectRepository.save(testProjectEntity);

        LocalDate today = LocalDate.now();
        Optional<ProjectEntity> result = underTestsProjectRepository.findById(testProjectEntity.getProjectId());

        assertThat(result).isPresent();
        assertThat(result.get().getProjectDueDate()).isBefore(today);
    }
}
