package ch.bzz.repository;

import ch.bzz.controller.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    // Optional helper method — this one is valid
    List<Account> findByName(String name);

    // Correct method to get all accounts for a project
    List<Account> findByProject_ProjectName(String projectName);

}
