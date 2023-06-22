package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.security.resource;
import lombok.*;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.security.domain.model.entity.Role;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class UserResource {
    private Long id;
    private String username;
    private String email;
    private Set<Role> roles;
}
