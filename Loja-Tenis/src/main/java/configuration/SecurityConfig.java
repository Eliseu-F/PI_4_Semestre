package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Filtros - quem tem ou não acesso
    @Bean
    SecurityFilterChain securityFiltarChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeConfig -> {
                    authorizeConfig.requestMatchers("/Signup").permitAll();
                    authorizeConfig.anyRequest().authenticated();
                })
                .formLogin(Customizer.withDefaults())
                .build();
    }
    
}
