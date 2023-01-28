package project.oobat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.oobat.Model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // find payment by username and status
    @Query("SELECT p FROM Payment p WHERE p.order.user.username = ?1 AND p.status = ?2")
    Payment findByUsernameAndStatus(String username, Payment.Status status);

    // check if user has unpaid payment or not (return true if user has unpaid payment)
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Payment p WHERE p.order.user.username = ?1 AND p.status = ?2")
    Boolean existsByUsernameAndStatus(String username, Payment.Status status);
}
