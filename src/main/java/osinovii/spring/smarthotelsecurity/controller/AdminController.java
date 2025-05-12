package osinovii.spring.smarthotelsecurity.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import osinovii.spring.smarthotelsecurity.service.RoomService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final RoomService roomService;

    public AdminController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/check-in/{roomNumber}")
    public void checkIn(@PathVariable String roomNumber) {
        roomService.checkIn(roomNumber);
    }

    @PostMapping("/check-out/{roomNumber}")
    public void checkOut(@PathVariable String roomNumber) {
        roomService.checkOut(roomNumber);
    }
}
