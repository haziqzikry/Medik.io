package project.oobat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.oobat.Model.Order;
import project.oobat.Model.Order.Status;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    //query to get the cart of a user
    @Query("SELECT o FROM Order o WHERE o.user.id = ?1 AND o.status = ?2 ORDER BY o.id DESC")
    public Order findByUserIdAndStatus(Long id, Status status);
    
    //query to get the cart of a user by username and sort the products in the cart by name
    @Query("SELECT o FROM Order o WHERE o.user.username = ?1 AND o.status = ?2 ORDER BY o.id DESC")
    public Order findByUsernameAndStatus(String username, Status status);

}
    
