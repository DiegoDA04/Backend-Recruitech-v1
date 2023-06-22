package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.domain.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.domain.model.enumeration.MessageType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
@Entity
@Table(name = "messages")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ms_id;

    private String content;
    private String sender;

}
