package project.oobat.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import project.oobat.Service.AppUserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private CustomLoginSuccessHandler successHandler;

    // @Autowired
    @Bean
    public AppUserService appUserService() {
        return new AppUserService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager (AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(appUserService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    
        http
            .authorizeRequests()
                .antMatchers("/login", "/register", "/register-admin", "/register/submit", "/login/submit").permitAll()
                // .antMatchers("/admin/**").hasRole("PHARMACIST")
                .antMatchers("/admin/**").hasAuthority("PHARMACIST")
                // .antMatchers("/user/**").hasRole("CUSTOMER")
                .antMatchers("/user/**").hasAuthority("CUSTOMER")
                .anyRequest().authenticated()
                .and()
            .csrf().disable().formLogin()
                .loginPage("/login")
                // .loginProcessingUrl("/login/submit")
                .successHandler(successHandler)
                .failureUrl("/login?fail=true")
                .usernameParameter("username")
                .passwordParameter("password")
                // .permitAll()
                .and()
            .logout()
                // .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .permitAll()
                .and()
            .exceptionHandling()
                .accessDeniedPage("/403");

        http.authenticationProvider(authenticationProvider());
        http.headers().frameOptions().sameOrigin();
        return http.build();

    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
    }

}
