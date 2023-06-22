package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.domain.service;

import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.domain.model.entity.Chat;

import java.util.List;
import java.util.Optional;

public interface ChatService {
    Optional<String> getById(String senderId, String recipientId, boolean createIfNoExist);
}
