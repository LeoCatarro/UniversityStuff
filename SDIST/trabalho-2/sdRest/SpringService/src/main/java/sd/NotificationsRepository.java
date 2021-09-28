package sd;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationsRepository extends JpaRepository<NotificationsEntity, Integer> {


    Optional<NotificationsEntity> findNotificationsEntityByEmail(String email);

}
