package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.service;

import org.springframework.stereotype.Service;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.domain.model.entity.Chat;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.domain.persistence.ChatRepository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.domain.service.ChatService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.security.domain.model.entity.User;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.security.domain.persistence.UserRepository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.shared.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

    private static final String ENTITY = "Chat";
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public ChatServiceImpl(ChatRepository chatRepository,
                           UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<String> getById(String senderId, String recipientId, boolean createIfNoExist) {

        return chatRepository
                .findBySenderIdAndRecipientId(senderId,recipientId)
                .map(Chat::getChatId)
                .or(()-> {
                    if(!createIfNoExist) {
                        return Optional.empty();
                    }

                    var chatId = String.format("%s_%s", senderId, recipientId);

                    Chat senderRecipient = new Chat()
                            .withChatId(chatId)
                            .withSenderId(senderId)
                            .withRecipientId(recipientId);
                    Chat recipientSender = new Chat()
                            .withChatId(chatId)
                            .withSenderId(recipientId)
                            .withRecipientId(senderId);

                    chatRepository.save(senderRecipient);
                    chatRepository.save(recipientSender);

                    return Optional.of(chatId);
                });
    }


}
