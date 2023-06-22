package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.service;

import org.springframework.stereotype.Service;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.domain.model.entity.Message;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.domain.model.enumeration.MessageStatus;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.domain.persistence.MessageRepository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.domain.service.ChatService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.domain.service.MessageService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.shared.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private static final String ENTITY = "Message";

    private final MessageRepository messageRepository;
    private final ChatService chatService;

    public MessageServiceImpl(MessageRepository messageRepository, ChatService chatService) {
        this.messageRepository = messageRepository;
        this.chatService = chatService;
    }

    @Override
    public Message create(Message message) {
        message.setStatus(MessageStatus.RECEIVED);

        return messageRepository.save(message);
    }

    @Override
    public Message getById(Long id) {

        return messageRepository
                .findById(id)
                .map(message -> {
                    message.setStatus(MessageStatus.RECEIVED);
                    return messageRepository.save(message);
                }).orElseThrow(() -> new ResourceNotFoundException("Message", id));
    }

    @Override
    public Long countNewMessages(String senderId, String recipientId) {
        return messageRepository
                .countBySenderIdAndRecipientIdAndStatus(senderId,recipientId,MessageStatus.RECEIVED);
    }

    @Override
    public List<Message> getAllChatMessages(String senderId, String recipientId) {
        var chatId = chatService.getById(senderId,recipientId, false);

        var messages = chatId.map(messageRepository::findByChatId).orElse(new ArrayList<>());

        if(messages.size() > 0) {
            updateStatuses(senderId, recipientId, MessageStatus.DELIVERED);
        }

        return messages;
    }

    @Override
    public void updateStatuses(String senderId, String recipientId, MessageStatus status) {
       messageRepository.updateStatuses(senderId, recipientId, status);
    }


}
