package com.upiiz.ExamenII.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    //SEcurity FILTER CHAIN
    //BEAN-SIngeton
    @Autowired
    AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .cors().and().csrf().disable()
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http->{
                    http.requestMatchers("/swagger-ui/**", "/v3/api-docs/**").hasAnyAuthority("CREATE");
                    http.requestMatchers(HttpMethod.GET, "/api/v1/reviews").hasAnyAuthority("READ");
                    http.requestMatchers(HttpMethod.GET, "/api/v1/reviews/{id}").hasAnyAuthority("READ");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/reviews").hasAnyAuthority("CREATE");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/reviews/{id}").hasAnyAuthority("UPDATE");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/reviews/{id}").hasAnyAuthority("DELETE");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/reviews/login").hasAnyAuthority("READ");
                    http.anyRequest().authenticated();
                })
                .build();
    }

    //MAneager
    @Bean
    public AuthenticationManager authenticationManager() throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    //Authenticanti provider
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return daoAuthenticationProvider;
    }

    //Password encoder
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    //User data servuice
    @Bean
    public UserDetailsService userDetailsService(){
        //Definir usuario en memoria
        //No vamos a obtenerlos de una basade datos
        UserDetails admin = User.withUsername("admin")
                .password("admin1234")
                .roles("ADMIN")
                .authorities("READ", "CREATE","UPDATE","DELETE")
                .build();

        UserDetails user = User.withUsername("user")
                .password("user1234")
                .roles("USER")
                .authorities("READ")
                .build();

        UserDetails moderator = User.withUsername("moderator")
                .password("moderator1234")
                .roles("MODERATOR")
                .authorities("READ", "UPDATE")
                .build();

        UserDetails editor = User.withUsername("editor")
                .password("editor1234")
                .roles("EDITOR")
                .authorities("UPDATE")
                .build();

        UserDetails developer = User.withUsername("developer")
                .password("developer1234")
                .roles("DEVELOPER")
                .authorities("READ", "WRITE", "UPDATE", "DELETE", "CREATE-USER")
                .build();

        UserDetails analyst = User.withUsername("analyst")
                .password("analyst1234")
                .roles("ANALYST")
                .authorities("READ", "UPDATE")
                .build();


        List<UserDetails> userDetailsList = new ArrayList<UserDetails>();


        userDetailsList.add(admin);
        userDetailsList.add(user);
        userDetailsList.add(moderator);
        userDetailsList.add(editor);
        userDetailsList.add(developer);
        userDetailsList.add(analyst);

        return new InMemoryUserDetailsManager(admin, user, moderator, editor, developer, analyst);
    }

}
