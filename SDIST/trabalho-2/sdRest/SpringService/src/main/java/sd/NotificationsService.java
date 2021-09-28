package sd;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;

@Service
public class NotificationsService {

    private NotificationsEntity notificationsEntity;
    private final NotificationsRepository notificationsRepository;

    public NotificationsService (NotificationsRepository notificationsRepository)
    {
        this.notificationsRepository = notificationsRepository;
    }

    @Transactional
    public void addNotification(String email, String notificationMsg) {

        if (notificationsRepository.findNotificationsEntityByEmail(email).isPresent())
        {
            notificationsEntity = notificationsRepository.findNotificationsEntityByEmail(email)
                    .orElseThrow( () -> new IllegalStateException("Email not found!\n"));

            notificationsEntity.setNotificationMsg(notificationMsg);
        }
        else
        {
            notificationsEntity = new NotificationsEntity(email, notificationMsg);

            notificationsRepository.save(notificationsEntity);
        }
    }

    @GetMapping
    public String getNotification(String email) {

        notificationsEntity = notificationsRepository.findNotificationsEntityByEmail(email)
                .orElseThrow( () -> new IllegalStateException("Email not found!\n"));

        return notificationsEntity.getNotificationMsg();
    }
}
