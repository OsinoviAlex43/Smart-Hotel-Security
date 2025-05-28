package osinovii.spring.smarthotelsecurity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import osinovii.spring.smarthotelsecurity.model.Admin;
import osinovii.spring.smarthotelsecurity.model.Room;
import osinovii.spring.smarthotelsecurity.repository.AdminRepository;
import osinovii.spring.smarthotelsecurity.repository.RoomRepository;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final RoomRepository roomRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    //Метод для получения всех админов
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    //Метод для получения всех комнат
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    //Метод заселения
    public void checkIn(String roomNumber) {
        Room room = roomRepository.findByRoomNumber(roomNumber);
        if (room == null) {
            throw new IllegalArgumentException("Room not found");
        }
        String newPassword = generateRandomPassword(); // Реализуй генерацию пароля
        room.setPassword(passwordEncoder.encode(newPassword));
        room.setOccupied(true);
        room.setCheckInDate(LocalDateTime.now());
        roomRepository.save(room);
        // Отправь пароль гостю (например, через email или SMS)
    }

    //Проверь насколько надежен пароль
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

    //Метод выселения
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
