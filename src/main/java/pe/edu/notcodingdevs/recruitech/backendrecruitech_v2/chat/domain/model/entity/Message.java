package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.domain.model.entity;

import jakarta.persistence.*;
import lombok.*;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.domain.model.enumeration.MessageStatus;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Company;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String chatId;
    private String senderId;
    private String recipientId;
    private String senderName;
    private String recipientName;
    private String content;
    private Date timestamp;
    private MessageStatus status;
}
