package sd;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "notifications")
public class NotificationsEntity{

    @Id
    @SequenceGenerator(name = "notifications_sequence", sequenceName = "notifications_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notifications_sequence")
    private Integer id;
    private String email;
    private String notificationMsg;

    public NotificationsEntity()
    {
        email = "";
        notificationMsg = "";
    }

    public NotificationsEntity(String email, String notificationMsg)
    {
        this.email = email;
        this.notificationMsg = notificationMsg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotificationMsg() {
        return notificationMsg;
    }

    public void setNotificationMsg(String notificationMsg) {
        this.notificationMsg = notificationMsg;
    }

    @Override
    public String toString() {
        return "NotificationsEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", notificationMsg='" + notificationMsg + '\'' +
                '}';
    }
}
