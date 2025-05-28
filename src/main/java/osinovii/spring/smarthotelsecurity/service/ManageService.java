package osinovii.spring.smarthotelsecurity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import osinovii.spring.smarthotelsecurity.model.Admin;
import osinovii.spring.smarthotelsecurity.model.Room;
import osinovii.spring.smarthotelsecurity.repository.AdminRepository;
import osinovii.spring.smarthotelsecurity.repository.RoomRepository;

@Service
@RequiredArgsConstructor
public class ManageService {

    private final RoomRepository roomRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    //Метод для добавления нового админа
    public void addingNewAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminRepository.save(admin);
    }

    //Метод для добавления комнаты в бд
    public void addingNewRoom(Room room) {
        room.setPassword(passwordEncoder.encode(room.getPassword()));
        roomRepository.save(room);
    }

}
