package br.dev.as.securanca.config;

import br.dev.as.securanca.security.JwtAuthFilter;
import br.dev.as.securanca.service.UserDetailServiceImpl;
import br.dev.as.securanca.support.encoder.PasswordEncoderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter ){
        this.jwtAuthFilter = jwtAuthFilter;
    }




//    @Bean
//    public PasswordEncoderImpl passwordEncoder(){
//        return new PasswordEncoderImpl();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);

        http.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        http.authorizeHttpRequests( r -> {
            r.requestMatchers("/public").permitAll();
            r.requestMatchers("/logout").permitAll();
            r.requestMatchers("/authenticate").permitAll();
            r.requestMatchers("/h2-console/**").permitAll();
            r.anyRequest().authenticated();
        } );

//        http.formLogin(Customizer.withDefaults()); PAGINA PRONTA
        http.httpBasic(Customizer.withDefaults());
//        http.oauth2Login(Customizer.withDefaults()); GOOGLE
        http.oauth2ResourceServer( conf -> conf.jwt(Customizer.withDefaults()));

        //config para correto funcionamento do h2-console
        http.headers(h -> h.frameOptions(f -> f.disable()));

        return http.build();
    }


}
