package pe.edu.notcodingdevs.recruitech.backendrecruitech.security.domain.model.entity;

import jakarta.persistence.*;
import lombok.*;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.security.domain.model.enumeration.Roles;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@With
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Roles name;
}
