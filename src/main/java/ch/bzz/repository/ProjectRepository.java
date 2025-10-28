package ch.bzz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ch.bzz.controller.Project;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, String> {
    // Ici la clé primate est le projectName (String)
    List<Project> findByProject_ProjectName(String projectName);
}
