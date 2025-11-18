package ch.bzz.controller;

import ch.bzz.generated.api.BookingApi;
import ch.bzz.generated.model.Booking;
import ch.bzz.generated.model.UpdateBookingsRequest;
import ch.bzz.repository.BookingRepository;
import ch.bzz.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookingApiController implements BookingApi {

    private final BookingRepository bookingRepository;
    private final JwtUtil jwtUtil;

    public BookingApiController(BookingRepository bookingRepository, JwtUtil jwtUtil) {
        this.bookingRepository = bookingRepository;
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
                    java.time.LocalDate.now(),
                    "Initial booking",
                    1000,
                    2000,
                    0.0f);
            return ResponseEntity.ok(List.of(dummy));
        }

        List<Booking> apiBookings = dbBookings.stream().map(db -> {
            Booking api = new Booking();
            api.setNumber(db.getId().intValue());
            api.setText(db.getText());
            api.setAmount(db.getAmount());
            api.setDebit(db.getDebitAccount() != null ? db.getDebitAccount().getAccountNumber().intValue() : null);
            api.setCredit(db.getCreditAccount() != null ? db.getCreditAccount().getAccountNumber().intValue() : null);
            api.setDate(db.getDate() != null
                    ? db.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                    : null);
            return api;
        }).collect(Collectors.toList());


        return ResponseEntity.ok(apiBookings);
    }

    @Override
    public ResponseEntity<Void> updateBookings(UpdateBookingsRequest updateBookingsRequest) {
        // TODO: Implement DB update logic
        return ResponseEntity.ok().build();
    }
}
