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
public class RoomService {

    //Дальнейшее развитие как сервисного класса для управления
    //комнатами : получения нужных характеристик и состояний

}