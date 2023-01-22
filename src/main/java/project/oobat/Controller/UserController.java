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
        return "user/home";
    }
    
    @GetMapping("/profile")
    public String profile() {
        return "user/profile";
    }
}
