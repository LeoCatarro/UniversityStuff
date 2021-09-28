package sd;

import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/notifications")
public class NotificationsController {

    private final NotificationsService notificationsService;

    public NotificationsController(NotificationsService notificationsService)
    {
        this.notificationsService  = notificationsService;
    }

    @PutMapping(value = "/addNotification")
    public void addNotification(@PathParam("email") String email, @PathParam("notificationMsg") String notificationMsg)
    {
        notificationsService.addNotification(email, notificationMsg);
    }

    @GetMapping(value = "/getNotification")
    public String getNotification(@PathParam("email") String email)
    {
        return notificationsService.getNotification(email);
    }

}
