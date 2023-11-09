package com.example.demo.Config;

import com.example.demo.DAO.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Configuration
@RequiredArgsConstructor
@Service
public class ApplicationConfiguration {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  jwtService JwtService;
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> userRepository.findByUserEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("User Does Not Found"));

    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authprovider=new DaoAuthenticationProvider();
        authprovider.setUserDetailsService(userDetailsService());
        authprovider.setPasswordEncoder(passwordEncoder());
        return authprovider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
           return  new BCryptPasswordEncoder();
    }

}
