package com.examen.web.config;

import com.examen.web.Servicio.UsuarioDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    private final UsuarioDetailsService usuarioDetailsService;

    @Autowired
    public SecurityConfig(UsuarioDetailsService usuarioDetailsService) {
        this.usuarioDetailsService = usuarioDetailsService;
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                       /* .requestMatchers("/usuarios/registro").permitAll()*/
                        .requestMatchers("/usuarios/index2").permitAll()
                       /* .requestMatchers("/login", "/error").permitAll()*/
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                       /* .loginPage("/login")*/
                        .successHandler(successHandler())
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .build();
    }


    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return ((request,response,authentication) -> {
            response.sendRedirect("/index");
        });
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {

        /*UserDetailsService: Esta interfaz es proporcionada por Spring Security y se usa para cargar información del
        usuario desde una fuente de datos, como una base de datos

        AuthenticationManagerBuilder es una clase en Spring Security que se usa para configurar la autenticación,
        como los detalles del usuario y el codificador de contraseñas
        */

        AuthenticationManagerBuilder authManager = http.getSharedObject(AuthenticationManagerBuilder.class);

        /*configurar el authManager para que carge el usuario de manera personalizada desde la base de datos

           passwordEncoder para codificar y comparar las contraseñas de los usuarios de forma segura.

        */
        authManager
                .userDetailsService(usuarioDetailsService)
                .passwordEncoder(passwordEncoder());
        return authManager.build();
    }

}
