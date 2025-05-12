package osinovii.spring.smarthotelsecurity.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "rooms")
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String roomNumber;

    @Column(nullable = false)
    private String password;

    private boolean isOccupied;

    private String role = "ROLE_GUEST";

    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
}
