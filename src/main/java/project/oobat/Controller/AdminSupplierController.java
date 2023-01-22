package project.oobat.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.oobat.Service.SupplierService;

@Controller
@RequestMapping("/admin/supplier")
public class AdminSupplierController {
    
    @Autowired
    private SupplierService supplierService;

    @GetMapping("/manage")
    public String manageSupplier() {
        return "admin/managesuppliers";
    }
}
