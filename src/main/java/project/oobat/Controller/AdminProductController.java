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
@RequestMapping("/admin/product")
public class AdminProductController {

    @Autowired
    private ProductService productService;
    
    @GetMapping("/manage")
    public String manageProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "admin/manageproduct";
    }

    @PostMapping("/add")
    public String addProduct(Product product) {
        productService.saveProduct(product);
        return "admin/manageproduct";
    }
}
