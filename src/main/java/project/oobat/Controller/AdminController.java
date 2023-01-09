package project.oobat.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.oobat.Model.Product;
import project.oobat.Service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;
    
    @GetMapping("/home")
    public String home() {
        return "adminindex";
    }

    @GetMapping("/profile")
    public String profile() {
        return "adminprofile";
    }

    @GetMapping("/manage-order")
    public String manageOrder() {
        return "manageorder";
    }

    @GetMapping("/manage-product")
    public String manageProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "manageproduct";
    }

    @PostMapping("/add-product")
    public String addProduct(Product product) {
        productService.saveProduct(product);
        return "manageproduct";
    }

    @GetMapping("/manage-stock")
    public String manageStock() {
        return "managestock";
    }

    @GetMapping("/manage-supplier")
    public String manageSupplier() {
        return "managesuppliers";
    }

}
