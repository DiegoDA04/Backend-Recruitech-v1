package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.chat.domain.model.entity;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatNotification {
    private Long id;
    private String senderId;
    private String senderName;
}
