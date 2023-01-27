package com.example.waa_first_demo.config;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig  {

    private final UserDetailsService userDetailsService;


    // 1st step authentication
    // first thing as we know we need an instance of AuthManager which deals with AuthProviders to perform authenticate() with takes the Authentication object and yes we do it on *UsernamePasswordAuthenticationToken*->extends->AbstractAuthenticationToken->implements-> Authentication-> which extends Principal and Serializable,
    // what changed: right now we are not taking it from the builder by extending WebSecurityConfigurer adapter anymore in the configure method, remember the builder was supporting multiple types of auth like JDBC, LDAP, etc.

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // 2.1 step
    // 2.2 step creating UserDetailsServiceCustom
    UserDetailsService customizedUserDetailsService() {
        return userDetailsService;
    }


    // 2.4 step
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();  // type of PasswordEncoder interface
    }



    // 2nd step
    // we have to set the type of authorization from a new interface DaoAuthenticationProvider
    // since we don't tell authentication manager what kind of authentication we want as we were doing previously we have to set it in new way, now we are using DAO pattern to set type of authentication we want which is userDetailsService in our case(we customize it) because no default implementation for JPA such as in jdbc or ldap or InMemory
    // also we have to set the passwordEncoder as we were doing since it is a required step and spring warn us to not use plain text
    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        // 2.1 and 2.2
        // so right now I realized that I need a user details service! let's go create it
        // will be a normal service in service package which implements UserDetailsService from spring to override loadUserByName() where I will get form userRepo that's why it should be injected there!
        // since we will cr create customized userDetails because there's no support for JPA we have to return USerDetailsService for authentication somehow to do its work and that's by implementing it but before that we have to inject UserDetails from Spring here, since it is sth needed considered security configuration

        // 2.3
        daoAuthenticationProvider.setUserDetailsService(customizedUserDetailsService());

        // 2.5
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());

        return daoAuthenticationProvider;
    }


     //3rd step: authorization
     //to work with authorization as you know we need to map paths to filter-mappings and httpSecurity instance follow the builder object which returns us a SecurityFilterChain
     //what changed: now we get it from FilterChain not from WebSecurityConfigurerAdapter and override configure that return void and take the HttpSecurity
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf().disable().cors()
                .and().authorizeHttpRequests()
                .requestMatchers("/api/v1/admin/*").hasRole("ADMIN")
                .and().authorizeHttpRequests().requestMatchers("/api/v1/users/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/*").permitAll().
                and().build();

    }


}
