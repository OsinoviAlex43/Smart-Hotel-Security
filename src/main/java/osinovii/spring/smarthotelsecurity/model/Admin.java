package osinovii.spring.smarthotelsecurity.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "admins")
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String adminLogin;

    @Column(nullable = false)
    private String password;

    private String role = "ROLE_ADMIN";
}
