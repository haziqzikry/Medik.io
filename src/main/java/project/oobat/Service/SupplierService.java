package project.oobat.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.oobat.Model.Supplier;
import project.oobat.Repository.SupplierRepository;

@Service
public class SupplierService {
    
    @Autowired
    private SupplierRepository supplierRepository;
}
