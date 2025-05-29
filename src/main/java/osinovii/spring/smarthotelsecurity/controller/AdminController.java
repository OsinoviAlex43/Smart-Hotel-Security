package osinovii.spring.smarthotelsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import osinovii.spring.smarthotelsecurity.model.Admin;
import osinovii.spring.smarthotelsecurity.model.Room;
import osinovii.spring.smarthotelsecurity.service.AdminService;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/all-admins")
    public List<Admin> getAllAdmins(){
        return adminService.getAllAdmins();
    }

    @GetMapping("/all-rooms")
    public List<Room> getAllRooms(){
        return adminService.getAllRooms();
    }

    @PostMapping("/check-in/{roomNumber}")
    public void checkIn(@PathVariable String roomNumber) {
        adminService.checkIn(roomNumber);
    }

    @PostMapping("/check-out/{roomNumber}")
    public void checkOut(@PathVariable String roomNumber) {
        adminService.checkOut(roomNumber);
    }

    @PostMapping("/new-room")
    public String newRoom(@RequestBody Room room) {
        adminService.addingNewRoom(room);
        return room.getRoomNumber() + " has been added to the room base";
    }

    @PostMapping("/new-admin")
    public String newAdmin(@RequestBody Admin admin) {
        adminService.addingNewAdmin(admin);
        return admin.getAdminLogin() + " has been added to the admins base";
    }
}
