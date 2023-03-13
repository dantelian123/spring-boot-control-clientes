package ec.com.gm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /*@Bean
    public UserDetailsService userDetailsService() {
        var user = User.withUsername("admin")
                .password("123")
                .roles("ADMIN")
                .build();
        var user1 = User.withUsername("user")
                .password("123")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user, user1);
    }
     */
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/editar/**", "/agregar/**", "/eliminar", "/guardar/**").hasRole("ADMIN");
                    auth.requestMatchers("/**", "/login", "/errores/**", "/webjars/**", "/layout/**", "/favicon.ico").permitAll();

                }).formLogin().loginPage("/login").and()
                .exceptionHandling().accessDeniedPage("/errores/403").and()
                .build();

    }

    //@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
