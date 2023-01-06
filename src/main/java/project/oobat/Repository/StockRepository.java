package project.oobat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.oobat.Model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    
}
