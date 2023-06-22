package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.location;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class CreateCountryResource {
    @NotBlank
    @NotNull
    @Length(max = 40)
    private String name;
}
