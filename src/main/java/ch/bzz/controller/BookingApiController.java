package ch.bzz.controller;

import ch.bzz.generated.api.BookingApi;
import ch.bzz.generated.model.Booking;
import ch.bzz.generated.model.UpdateBookingsRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookingApiController implements BookingApi {

    private final List<Booking> bookings = new ArrayList<>();

    @Override
    public ResponseEntity<List<Booking>> getBookings() {
        // Example implementation – return all bookings
        return ResponseEntity.ok(bookings);
    }

    @Override
    public ResponseEntity<Void> updateBookings(UpdateBookingsRequest updateBookingsRequest) {
        // TODO: implement logic to update bookings in DB
        return ResponseEntity.ok().build();
    }
}
