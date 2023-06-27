package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.api.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.domain.model.entity.ChatNotification;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.domain.model.entity.Message;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.domain.service.ChatService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.domain.service.MessageService;

@Controller
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatService chatService;
    private final MessageService messageService;
    private final ObjectMapper objectMapper;

    public ChatController(SimpMessagingTemplate simpMessagingTemplate, ChatService chatService, MessageService messageService, ObjectMapper objectMapper) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.chatService = chatService;
        this.messageService = messageService;
        this.objectMapper = objectMapper;
    }

    @MessageMapping("/chat")
    public void processMessage(@Payload Message chatMessage) {

        var chatId = chatService
                .getById(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
        chatMessage.setChatId(chatId.get());

        Message saved = messageService.create(chatMessage);

        simpMessagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId(),"/queue/messages",
                new ChatNotification(
                        saved.getId(),
                        saved.getSenderId(),
                        saved.getSenderName()));
    }

    @GetMapping("/messages/{senderId}/{recipientId}/count")
    public ResponseEntity<Long> countNewMessages(
            @PathVariable String senderId,
            @PathVariable String recipientId) {

        return ResponseEntity
                .ok(messageService.countNewMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<?> findChatMessages ( @PathVariable String senderId,
                                                @PathVariable String recipientId) {
        return ResponseEntity
                .ok(messageService.getAllChatMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<?> findMessage (@PathVariable Long id) {
        return ResponseEntity
                .ok(messageService.getById(id));
    }
}
