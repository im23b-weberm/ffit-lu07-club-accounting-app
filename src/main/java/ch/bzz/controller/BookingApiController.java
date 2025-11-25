package ch.bzz.controller;

import ch.bzz.generated.api.BookingApi;
import ch.bzz.generated.model.Booking;
import ch.bzz.generated.model.BookingUpdate;
import ch.bzz.generated.model.UpdateBookingsRequest;
import ch.bzz.repository.AccountRepository;
import ch.bzz.repository.BookingRepository;
import ch.bzz.repository.ProjectRepository;
import ch.bzz.util.JwtUtil;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class BookingApiController implements BookingApi {

    private final BookingRepository bookingRepository;
    private final ProjectRepository projectRepository;
    private final JwtUtil jwtUtil;
    private final AccountRepository accountRepository;

    public BookingApiController(BookingRepository bookingRepository, ProjectRepository projectRepository, AccountRepository accountRepository, JwtUtil jwtUtil) {
        this.bookingRepository = bookingRepository;
        this.projectRepository = projectRepository;
        this.accountRepository = accountRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public ResponseEntity<List<Booking>> getBookings() {
        // 1️⃣ Extract project name from JWT
        String projectName = jwtUtil.verifyTokenAndExtractSubject();

        // 2️⃣ Load all bookings for this project
        List<ch.bzz.controller.Booking> dbBookings =
                bookingRepository.findByProject_ProjectName(projectName);

        // 3️⃣ If DB empty, return a default dummy
        if (dbBookings.isEmpty()) {
            Booking dummy = new Booking(1,
                    LocalDate.now(),
                    "Initial booking",
                    1000,
                    2000,
                    0.0f);
            return ResponseEntity.ok(List.of(dummy));
        }

        List<Booking> apiBookings = dbBookings.stream().map(db -> {
            Booking api = new Booking();
            api.setNumber(db.getBookingNumber());
            api.setText(db.getText());
            api.setAmount(db.getAmount());
            api.setDebit(db.getDebitAccount() != null ? db.getDebitAccount().getAccountNumber().intValue() : null);
            api.setCredit(db.getCreditAccount() != null ? db.getCreditAccount().getAccountNumber().intValue() : null);
            Date dbd = db.getDate();

            api.setDate(dbd == null ? null : LocalDate.of(dbd.getYear(), dbd.getMonth()+1, dbd.getDay()));
            return api;
        }).collect(Collectors.toList());


        return ResponseEntity.ok(apiBookings);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> updateBookings(UpdateBookingsRequest updateBookingsRequest) {
        // 1) Projektname aus JWT extrahieren
        String projectName = jwtUtil.verifyTokenAndExtractSubject();

        Project project = projectRepository.findById(projectName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));

        // 2) Bestehende Bookings laden
        // List<ch.bzz.controller.Booking> existingBookings = bookingRepository.findByProject(project);

        // 3) Jedes API-Booking verarbeiten

        for (BookingUpdate api : updateBookingsRequest.getEntries()) {

            log.debug("Processing booking id={} date={}", api.getId(), api.getDate());

            // === LÖSCHEN ===
            // Wenn "date": null → Eintrag löschen
            if (!api.getDate().isPresent() || api.getDate().get() == null) {
                bookingRepository.deleteByIdAndProject(api.getId().longValue(), project);
                log.debug("Deleted booking {}", api.getId());
                continue;
            }

            // === EXISTENZ PRÜFEN ===
            Optional<ch.bzz.controller.Booking> dbOpt =
                    bookingRepository.findByIdAndProject(api.getId().longValue(), project);

            ch.bzz.controller.Booking dbBooking;

            if (dbOpt.isPresent()) {
                // === UPDATE ===
                dbBooking = dbOpt.get();
                log.debug("Updating booking {}", api.getId());

            } else {
                // === INSERT ===
                dbBooking = new ch.bzz.controller.Booking();
                dbBooking.setBookingNumber(api.getId());
                dbBooking.setProject(project);

                log.debug("Creating booking {}", api.getId());
            }

            // === FELDER setzen ===

            dbBooking.setText(api.getText().get());
            dbBooking.setAmount(api.getAmount().get());

            // Date = LocalDate → DB: java.sql.Date oder LocalDate abhängig deiner Entity
            LocalDate ld = api.getDate().get();
            dbBooking.setDate(new Date(ld.getYear(), ld.getMonthValue()-1, ld.getDayOfMonth()));

            // Debit-Konto laden
            Account debitAcc = accountRepository
                    .findByAccountNumberAndProject(api.getDebit().get().longValue(), project)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "Debit account not found: " + api.getDebit()));

            dbBooking.setDebitAccount(debitAcc);

            // Credit-Konto laden
            Account creditAcc = accountRepository
                    .findByAccountNumberAndProject(api.getCredit().get().longValue(), project)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "Credit account not found: " + api.getCredit()));

            dbBooking.setCreditAccount(creditAcc);

            // === SPEICHERN ===
            bookingRepository.save(dbBooking);
        }

        log.debug("Committed {} booking updates", updateBookingsRequest.getEntries().size());
        return ResponseEntity.ok().build();
    }

}
