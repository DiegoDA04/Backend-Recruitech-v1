package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.domain.model.entity.TestMessage;

@RestController
public class WebSocketController {

    private final SimpMessagingTemplate template;

    public WebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@RequestBody TestMessage message) {
        template.convertAndSend("/topic/message", message);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @MessageMapping("/chat")
    public void receiveMessage(@Payload TestMessage textMessageDTO) {
        template.convertAndSend("/topic/message", textMessageDTO);
    }

    @SendTo("/topic/message")
    public TestMessage broadcastMessage(@Payload TestMessage textMessageDTO) {
        return textMessageDTO;
    }
}
