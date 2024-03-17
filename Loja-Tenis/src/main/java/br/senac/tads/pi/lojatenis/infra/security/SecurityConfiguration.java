package br.senac.tads.pi.lojatenis.infra.security;

import br.senac.tads.pi.lojatenis.model.UsuarioDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

    @Autowired
    private UsuarioDetailsService usuarioDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry->{

           registry.requestMatchers("/signup", "/", "/images/**", "/img/**", "/css/**", "style", "/js/**", "/index", "/h2-console").permitAll();

           registry.requestMatchers("/home").permitAll();

           registry.requestMatchers("/index.html").hasRole("USER");

           registry.requestMatchers("/usuarios/**").hasRole("ADMIN");

           registry.requestMatchers("/produtos/**").hasRole("USER");

           registry.anyRequest().authenticated();

        })
                .formLogin(httpSecurityFormLoginConfigurer -> {
                    httpSecurityFormLoginConfigurer
                            .loginPage("/signup")
                            .usernameParameter("email")
                            .successHandler(new AuthenticationSuccessHandler())
                            .permitAll();
                })
                .logout(LogoutConfigurer -> LogoutConfigurer
                        .logoutSuccessUrl("/signup")
                        .logoutUrl("/logout")
                        .permitAll())
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(){

        return usuarioDetailsService;
    }



    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(usuarioDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
