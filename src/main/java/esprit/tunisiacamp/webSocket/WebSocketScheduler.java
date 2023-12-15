package esprit.tunisiacamp.webSocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class WebSocketScheduler {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebSocketScheduler(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Scheduled(cron = "5 * * * * *")
    public void pushMessages() {
        String message = "Hello, world!";
        messagingTemplate.convertAndSend("/topic/greetings", message);
    }
}
