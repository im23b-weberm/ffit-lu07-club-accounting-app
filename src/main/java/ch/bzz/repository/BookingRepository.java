package ch.bzz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import ch.bzz.controller.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Trouver les bookings d’un projet donné
    List<Booking> findByProject_ProjectName(String projectName);

    // Trouver les bookings liés à un compte débité
    List<Booking> findByDebitAccount_Id(Long accountId);

    // Trouver les bookings liés à un compte crédité
    List<Booking> findByCreditAccount_Id(Long accountId);
}
