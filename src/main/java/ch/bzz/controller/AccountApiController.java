package ch.bzz.controller;

import ch.bzz.generated.api.AccountApi;
import ch.bzz.generated.api.ProjectApi;
import ch.bzz.generated.model.*;
import ch.bzz.generated.model.Account;
import ch.bzz.repository.AccountRepository;
import ch.bzz.repository.ProjectRepository;
import ch.bzz.util.JwtUtil;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class AccountApiController implements AccountApi {

    private final AccountRepository accountRepository;
    private final ProjectRepository projectRepository;
    private final JwtUtil jwtUtil;

    public AccountApiController(AccountRepository accountRepository,
                                ProjectRepository projectRepository,
                                JwtUtil jwtUtil) {
        this.accountRepository = accountRepository;
        this.projectRepository = projectRepository;
        this.jwtUtil = jwtUtil;
    }
    @Override
    public ResponseEntity<List<Account>> getAccounts() {

        String projectName = jwtUtil.verifyTokenAndExtractSubject();

        // Load DB accounts for this project
        List<ch.bzz.controller.Account> dbAccounts =
                accountRepository.findByProject_ProjectName(projectName);

        // If empty → return dummy accounts (optional)
        if (dbAccounts.isEmpty()) {
            Account dummy = new Account();
            dummy.setNumber(1000);
            dummy.setName("Kasse");

            return ResponseEntity.ok(List.of(dummy));
        }

        // Convert DB model → API model
        List<Account> result = dbAccounts.stream()
                .map(db -> {
                    Account api = new Account();
                    api.setNumber(db.getAccountNumber().intValue()); // convert Long → Integer
                    api.setName(db.getName());
                    return api;
                })
                .toList();

        return ResponseEntity.ok(result);
    }


    @Transactional
    @Override
    public ResponseEntity<Void> updateAccounts(UpdateAccountsRequest updateAccountsRequest) {

        // 1. Projekt aus JWT extrahieren
        String projectName = jwtUtil.verifyTokenAndExtractSubject();
        Project project = projectRepository.findById(projectName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));

        // 2. Bestehende Accounts aus der DB laden
        // List<ch.bzz.controller.Account> existingAccounts = accountRepository.findByProject_ProjectName(projectName);

        // 3. Accounts aus der Anfrage verarbeiten
        for (AccountUpdate apiAccount : updateAccountsRequest.getAccounts()) {
            log.debug("Processing account {} {}", apiAccount.getNumber(), apiAccount.getName().get());

            // Prüfen, ob der Account bereits existiert
            // ch.bzz.controller.Account dbAccount = existingAccounts.stream()
            //        .filter(a -> a.getAccountNumber().intValue() == apiAccount.getNumber())
            //        .findFirst()
            //        .orElse(null);
            if (apiAccount.getName().get() == null) {
                // Name null → Account löschen
                    accountRepository.deleteByAccountNumberAndProject(apiAccount.getNumber().longValue(), project);
                log.debug("Deleting account {} {}", apiAccount.getNumber(), apiAccount.getName().get());
            } else {
                Optional<ch.bzz.controller.Account> accountOptional = accountRepository.findByAccountNumberAndProject(apiAccount.getNumber().longValue(), project);
                ch.bzz.controller.Account dbAccount;
                // Name nicht null → anlegen oder aktualisieren
                if (accountOptional.isPresent()) {
                    dbAccount = accountOptional.get();

                    log.debug("Updating account {} {}", apiAccount.getNumber(), apiAccount.getName().get());
                }else{
                    dbAccount = new ch.bzz.controller.Account();
                    dbAccount.setAccountNumber(apiAccount.getNumber().longValue());
                    dbAccount.setProject(project);

                    log.debug("Creating account {} {}", apiAccount.getNumber(), apiAccount.getName().get());
                }
                dbAccount.setName(apiAccount.getName().get());
                accountRepository.save(dbAccount);

            }
        }

        log.debug("Committing {} changes", updateAccountsRequest.getAccounts().size());
        return ResponseEntity.ok().build();
    }
}
