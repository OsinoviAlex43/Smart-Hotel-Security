package osinovii.spring.smarthotelsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import osinovii.spring.smarthotelsecurity.model.Room;
import osinovii.spring.smarthotelsecurity.repository.RoomRepository;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/guest/**").hasRole("GUEST")
                        .anyRequest().authenticated()
                ).formLogin(form -> form  // Новый стиль вместо formLogin()
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(logout -> logout  // Новый стиль вместо logout()
                        .logoutSuccessUrl("/login")
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(RoomRepository roomRepository) {
        return username -> {
            Room room = roomRepository.findByRoomNumber(username);
            if (room == null) {
                throw new UsernameNotFoundException("Room not found: " + username);
            }
            return new org.springframework.security.core.userdetails.User(
                    room.getRoomNumber(),
                    room.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority(room.getRole()))
            );
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}