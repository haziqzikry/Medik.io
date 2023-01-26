package project.oobat.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.oobat.Model.AppUser;
import project.oobat.Service.AppUserService;
import project.oobat.Service.ProductService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/home")
    public String home() {
        return "user/home";
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        model.addAttribute("user", appUserService.loadUserByUsername(principal.getName()));
        return "user/profile";
    }

    @PostMapping("/profile")
    public String profile(AppUser appUser) {
        appUserService.updateProfile(appUser);

        return "redirect:user/profile";
    }

}
