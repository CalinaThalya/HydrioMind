package br.com.fiap.hydriomind.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.com.fiap.hydriomind.service.UsuarioService;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final UsuarioService usuarioService;

    // Cria o UserDetailsService com o seu UsuarioService
    @Bean
    public UserDetailsService userDetailsService() {
        return usuarioService;
    }

    // Cria o AuthenticationProvider com o UserDetailsService e o PasswordEncoder
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(usuarioService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // Cria o PasswordEncoder (BCrypt)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configuração de segurança HTTP
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // Desativa o CSRF (útil para APIs REST ou SPAs)
                .authorizeHttpRequests(registry -> {
                    // Permite acesso livre às páginas de signup, login e recursos estáticos
                    registry.requestMatchers("/req/signup", "/req/login", "/css/**", "/js/**", "/home").permitAll();
                    registry.anyRequest().authenticated(); // Requer autenticação para qualquer outra requisição
                })
                .formLogin(httpForm -> {
                    httpForm.loginPage("/req/login")  // Página de login personalizada
                            .permitAll()
                            .defaultSuccessUrl("/home", true); // Redireciona para home após login
                })
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL de logout
                        .logoutSuccessUrl("/req/login?logout=true") // URL após sucesso de logout
                        .permitAll() // Permite logout sem autenticação
                );

        return httpSecurity.build();
    }

    // Criação do AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(usuarioService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
}
