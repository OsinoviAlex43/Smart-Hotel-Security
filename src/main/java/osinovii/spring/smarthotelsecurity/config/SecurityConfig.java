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
import osinovii.spring.smarthotelsecurity.model.Admin;
import osinovii.spring.smarthotelsecurity.model.Room;
import osinovii.spring.smarthotelsecurity.repository.AdminRepository;
import osinovii.spring.smarthotelsecurity.repository.RoomRepository;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                //.hasRole("ADMIN")
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/logout", "/resources/**", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/guest/**").hasRole("GUEST")
                        .anyRequest().authenticated()
                ).formLogin(form -> form  // Новый стиль вместо formLogin()
                        .defaultSuccessUrl("/api/admin/all-admins")
                        .permitAll()
                )
                .logout(logout -> logout  // Новый стиль вместо logout()
                        .logoutUrl("/logout") // URL для выхода
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(RoomRepository roomRepository, AdminRepository adminRepository) {
        return username -> {
            Room room = roomRepository.findByRoomNumber(username);
            if (room != null) {
                return new org.springframework.security.core.userdetails.User(
                        room.getRoomNumber(),
                        room.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority(room.getRole()))
                );
            }
            Admin admin = adminRepository.findByAdminLogin(username);
            if (admin != null) {
                return new org.springframework.security.core.userdetails.User(
                        admin.getAdminLogin(),
                        admin.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority(admin.getRole()))
                );
            }
            throw new UsernameNotFoundException("User not found: " + username);
        };
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}