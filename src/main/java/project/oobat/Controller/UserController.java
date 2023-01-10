package project.oobat.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.oobat.Service.ProductService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ProductService productService;
    
    @GetMapping("/home")
    public String home() {
        return "userindex";
    }
    
    @GetMapping("/profile")
    public String profile() {
        return "userprofile";
    }

    @GetMapping("/view-order")
    public String viewOrder() {
        return "vieworder";
    }

    @GetMapping("/view-product")
    public String viewProduct(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product";
    }
    
    @GetMapping("/view-cart")
    public String viewCart() {
        return "cart";
    }

}
