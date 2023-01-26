package project.oobat.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.oobat.Model.Supplier;
import project.oobat.Service.SupplierService;

@Controller
@RequestMapping("/admin/supplier")
public class AdminSupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/manage")
    public String manageSupplier(Model model) {
        Supplier supplier = new Supplier();
        model.addAttribute("supplier", supplier);
        return "admin/managesuppliers";
    }

    @PostMapping("/add")
    public String addSupplier(Supplier supplier) {
        supplierService.saveSupplier(supplier);
        return "admin/managesuppliers";
    }

}
