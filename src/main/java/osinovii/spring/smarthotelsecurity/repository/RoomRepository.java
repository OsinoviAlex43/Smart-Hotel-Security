package osinovii.spring.smarthotelsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import osinovii.spring.smarthotelsecurity.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByRoomNumber(String roomNumber);
}