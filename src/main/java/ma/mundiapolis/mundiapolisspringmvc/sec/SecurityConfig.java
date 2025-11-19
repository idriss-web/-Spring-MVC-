package ma.mundiapolis.mundiapolisspringmvc.sec;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder) {
        PasswordEncoder encoder = passwordEncoder();
        System.out.println("==========");

        String encodedPWD = encoder.encode("123456");
        System.out.println(encodedPWD);

        System.out.println("==========");


        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build(),
                User.withUsername("user2").password(passwordEncoder.encode("1234")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("USER", "ADMIN").build()
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .formLogin(withDefaults())
                .authorizeHttpRequests(ar -> ar.requestMatchers("/user/**").hasRole("USER"))
                .authorizeHttpRequests(ar -> ar.requestMatchers("/admin**/**").hasRole("ADMIN"))
                .authorizeHttpRequests(ar -> ar.requestMatchers("/user/**").permitAll())

                .authorizeHttpRequests(ar -> ar.anyRequest().authenticated())
                .build();
    }
}
