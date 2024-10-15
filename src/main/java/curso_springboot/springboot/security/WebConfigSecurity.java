package curso_springboot.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity {

    @Autowired
    private ImplementacaoUserDetailServices userDetailServices;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/").authenticated()
                        .anyRequest().authenticated())
                .formLogin(form -> form.permitAll()
                        .loginPage("/login")
                        .defaultSuccessUrl("/cadastroPessoa", true)
                        .failureHandler(new AuthenticationFail()))
                .logout(logout -> logout.logoutSuccessUrl("/login?logout"));

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        configure(authenticationManagerBuilder);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }


    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(userDetailServices)
                .passwordEncoder(passwordEncoder());

    }

}
