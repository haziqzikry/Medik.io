package project.oobat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.oobat.Model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
