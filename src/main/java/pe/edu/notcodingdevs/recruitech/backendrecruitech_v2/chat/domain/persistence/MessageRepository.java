package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.domain.model.entity.Message;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.domain.model.enumeration.MessageStatus;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChatId(String chatId);
    Long countBySenderIdAndRecipientIdAndStatus(String senderId, String recipientId, MessageStatus status);

    @Modifying
    @Query("UPDATE Message m SET m.status =:status WHERE m.senderId =:senderId AND m.recipientId =:recipientId")
    void updateStatuses(@Param("senderId") String senderId, @Param("recipientId") String recipientId, @Param("status") MessageStatus status);
}
