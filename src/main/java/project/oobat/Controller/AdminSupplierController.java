package project.oobat.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.oobat.Model.Supplier;
import project.oobat.Repository.SupplierRepository;
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
        Iterable<Supplier> suppliers = supplierService.getAllSuppliers();
        model.addAttribute("suppliers", suppliers);
        return "admin/managesuppliers";
    }

    @PostMapping("/add")
    public String addSupplier(Supplier supplier) {
        supplierService.saveSupplier(supplier);
        return "redirect:/admin/supplier/manage";
    }

    @GetMapping("/delete/{id}")
    public String removefromSuppliers(@PathVariable("id") Long suppliedId) {
        Supplier supplier = supplierService.getSupplierbyID(suppliedId);
        supplierService.deleteSupplier(supplier);
        return "redirect:/admin/supplier/manage";
    }

    @GetMapping("/edit/{id}")
    public String editSupplier(@PathVariable("id") Long suppliedId, Model model) {
        Supplier supplier = supplierService.getSupplierbyID(suppliedId);
        model.addAttribute("supplier", supplier);
        Iterable<Supplier> suppliers = supplierService.getAllSuppliers();
        model.addAttribute("suppliers", suppliers);
        supplierService.saveSupplier(supplier);

        return "admin/editsuppliers";
    }

}
