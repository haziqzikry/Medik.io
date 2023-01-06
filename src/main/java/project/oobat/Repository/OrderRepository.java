package project.oobat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.oobat.Model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
