package osinovii.spring.smarthotelsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import osinovii.spring.smarthotelsecurity.model.Admin;

public interface AdminRepository extends JpaRepository<Admin,Long> {
Admin findByAdminLogin (String adminLogin);
}
