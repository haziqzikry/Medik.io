package project.oobat.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/cart")
    public String cart() {
        return "Cart";
    }

    @GetMapping("/manageorder")
    public String manageorder() {
        return "manageorder";
    }

    @GetMapping("/manageproduct")
    public String manageproduct() {
        return "manageproduct";
    }

    @GetMapping("/manageordercust")
    public String manageordercust() {
        return "manageordercust";
    }

    @GetMapping("/managestock")
    public String managestock() {
        return "ManageStock";
    }

    @GetMapping("/managesuppliers")
    public String managesuppliers() {
        return "ManageSuppliers";
    }

    @GetMapping("/product")
    public String product() {
        return "product";
    }

    @GetMapping("/manageprofile")
    public String manageprofile() {
        return "manageprofile";
    }

}
