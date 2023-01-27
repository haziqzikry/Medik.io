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

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/home")
    public String home() {
        return "admin/home";
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        model.addAttribute("user", appUserService.loadUserByUsername(principal.getName()));
        return "admin/profile";
    }

    @PostMapping("/profile")
    public String profile(AppUser appUser, Principal principal) {
        AppUser updatedUser = appUserService.loadUserByUsername(principal.getName());
        updatedUser.setName(appUser.getName());
        updatedUser.setAddress(appUser.getAddress());
        updatedUser.setPhone(appUser.getPhone());
        updatedUser.setEmail(appUser.getEmail());
        appUserService.updateProfile(updatedUser);
        return "redirect:/admin/profile";
    }
}
