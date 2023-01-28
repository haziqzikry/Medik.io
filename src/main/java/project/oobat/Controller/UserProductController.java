package project.oobat.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import project.oobat.Service.ProductService;

@Controller
@RequestMapping("/user/product")
public class UserProductController {
    
    @Autowired
    private ProductService productService;
    
    @GetMapping("/view")
    public String viewProduct(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "user/product";
    }

    @GetMapping("/view/{id}")
    public String viewProductById(Model model, @PathVariable Long id) {
        model.addAttribute("product", productService.getProductById(id));
        return "user/product";
    }
}
