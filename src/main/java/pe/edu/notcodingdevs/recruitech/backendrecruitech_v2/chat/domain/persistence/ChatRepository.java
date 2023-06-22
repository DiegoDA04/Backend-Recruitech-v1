package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.domain.model.entity.Chat;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    Optional<Chat> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
