package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.domain.service;

import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.domain.model.entity.Chat;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.domain.model.entity.Message;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.domain.model.enumeration.MessageStatus;

import java.util.List;

public interface MessageService {
    Message create(Message message);

    Message getById(Long id);

    Long countNewMessages(String senderId, String recipientId);

    List<Message> getAllChatMessages(String senderId, String recipientId);

    void updateStatuses(String senderId, String recipientId, MessageStatus status);
}
