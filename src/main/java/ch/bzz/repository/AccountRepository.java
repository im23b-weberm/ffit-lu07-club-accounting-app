package ch.bzz.repository;

import ch.bzz.controller.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> { // Long ist der Datentyp des Primärschlüssels
    List<Account> findByAccountName(String name);
    List<Account> findByAccountProjectName(String project);
}

