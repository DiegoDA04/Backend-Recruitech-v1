package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.location;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Country;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class CreateLocationResource {
    @NotBlank
    @NotNull
    @Length(max = 40)
    private String name;
}
