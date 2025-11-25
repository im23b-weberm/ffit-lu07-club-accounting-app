package ch.bzz.repository;

import ch.bzz.controller.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

import ch.bzz.controller.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Trouver les bookings d’un projet donné
    List<Booking> findByProject_ProjectName(String projectName);

    // Trouver les bookings liés à un compte débité
    List<Booking> findByDebitAccount_Id(Long accountId);

    // Trouver les bookings liés à un compte crédité
    List<Booking> findByCreditAccount_Id(Long accountId);

    List<Booking> findByProject(Project project);

    void deleteByIdAndProject(long l, Project project);

    Optional<Booking> findByIdAndProject(long l, Project project);
}
