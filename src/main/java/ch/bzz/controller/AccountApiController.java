package ch.bzz.controller;

import ch.bzz.generated.api.AccountApi;
import ch.bzz.generated.api.ProjectApi;
import ch.bzz.generated.model.Account;
import ch.bzz.generated.model.LoginProject200Response;
import ch.bzz.generated.model.LoginRequest;
import ch.bzz.generated.model.UpdateAccountsRequest;
import ch.bzz.repository.AccountRepository;
import ch.bzz.util.JwtUtil;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;



import java.util.List;

@RestController
public class AccountApiController implements AccountApi {

    private final AccountRepository accountRepository;
    private final JwtUtil jwtUtil;

    private String jwtSecret;

    public AccountApiController(AccountRepository accountRepository, JwtUtil jwtUtil) {
        this.accountRepository = accountRepository;
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



    @Override
    public ResponseEntity<Void> updateAccounts(UpdateAccountsRequest updateAccountsRequest) {
        return null;
    }
}
