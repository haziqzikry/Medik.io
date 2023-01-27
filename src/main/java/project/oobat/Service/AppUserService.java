package project.oobat.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import project.oobat.Model.AppUser;
import project.oobat.Model.AppUserDetails;
import project.oobat.Model.AppUser.Role;
import project.oobat.Repository.AppUserRepository;

@Service
public class AppUserService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public AppUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found"));
    }

    public void registerCustomer(AppUser appUser) {
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        // line below could be problematic
        appUser.setRole(Role.CUSTOMER);
        appUserRepository.save(appUser);
    }

    public void registerPharmacist(AppUser appUser) {
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        // line below could be problematic
        appUser.setRole(Role.PHARMACIST);
        appUserRepository.save(appUser);
    }

    public List<Object> isUserExist(String username) {
        boolean isExist = false;
        String message = "";
        Optional<AppUser> result = appUserRepository.findByUsername(username);
        if (result.isPresent()) {
            isExist = true;
            message = "Username already exist";
        }
        System.out.println("isExist: " + isExist);
        System.out.println("message: " + message);
        return Arrays.asList(isExist, message);
    }

    public void updateProfile(AppUser appUser) {
        appUserRepository.save(appUser);
    }

}
