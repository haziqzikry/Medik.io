package project.oobat.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @GetMapping("/home")
    public String home() {
        return "adminindex";
    }

    @GetMapping("/profile")
    public String profile() {
        return "adminprofile";
    }
}
