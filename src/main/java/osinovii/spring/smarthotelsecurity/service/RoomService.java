package osinovii.spring.smarthotelsecurity.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import osinovii.spring.smarthotelsecurity.model.Room;
import osinovii.spring.smarthotelsecurity.repository.RoomRepository;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final PasswordEncoder passwordEncoder;

    public RoomService(RoomRepository roomRepository, PasswordEncoder passwordEncoder) {
        this.roomRepository = roomRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void checkIn(String roomNumber) {
        Room room = roomRepository.findByRoomNumber(roomNumber);
        if (room == null) {
            throw new IllegalArgumentException("Room not found");
        }
        String newPassword = generateRandomPassword(); // Реализуйте генерацию пароля
        room.setPassword(passwordEncoder.encode(newPassword));
        room.setOccupied(true);
        room.setCheckInDate(LocalDateTime.now());
        roomRepository.save(room);
        // Отправьте пароль гостю (например, через email или SMS)
    }

    //Предсказуемая генерация пароля, потребуются изменения
    private String generateRandomPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
        SecureRandom random = new SecureRandom();
        int length = 12; // Увеличение длины для повышения безопасности
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        return password.toString();
    }

    public void checkOut(String roomNumber) {
        Room room = roomRepository.findByRoomNumber(roomNumber);
        if (room == null) {
            throw new IllegalArgumentException("Room not found");
        }
        String newPassword = generateRandomPassword();
        room.setPassword(passwordEncoder.encode(newPassword));
        room.setOccupied(false);
        room.setCheckOutDate(LocalDateTime.now());
        roomRepository.save(room);
    }

}