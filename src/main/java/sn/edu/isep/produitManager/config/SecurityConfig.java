package sn.edu.isep.produitManager.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import sn.edu.isep.produitManager.securities.CustomUserDetailsService;

@Configuration
public class SecurityConfig {

        private final CustomUserDetailsService userDetailsService;


        public SecurityConfig(CustomUserDetailsService userDetailsService) {
            this.userDetailsService = userDetailsService;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        // Enregistre un AuthenticationProvider basé sur ton CustomUserDetailsService
        @Bean
        public DaoAuthenticationProvider daoAuthenticationProvider() {
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            provider.setUserDetailsService(userDetailsService);
            provider.setPasswordEncoder(passwordEncoder());
            return provider;
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
            return config.getAuthenticationManager();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(csrf -> csrf.disable())
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(auth -> auth
                            //.requestMatchers("/api/produits", "/api/produits/", "/api/produits/{id}", "/swagger-ui/", "/v3/api-docs/").permitAll()
                            //.requestMatchers("/api/produits").hasAnyRole("CLIENT", "MANAGER")
                            //.requestMatchers("/api/produits/").hasRole("MANAGER")
                            .anyRequest().permitAll()
                    )
                    .httpBasic
                            (httpBasic -> {

                            });

            return http.build();
}
    }



