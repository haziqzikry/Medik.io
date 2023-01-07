package project.oobat.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    
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
    public String viewProduct() {
        return "product";
    }
    
    @GetMapping("/view-cart")
    public String viewCart() {
        return "cart";
    }

}
