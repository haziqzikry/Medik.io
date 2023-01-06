package project.oobat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.oobat.Model.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    
}
