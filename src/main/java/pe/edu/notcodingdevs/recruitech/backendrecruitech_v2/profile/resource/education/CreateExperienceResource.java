package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.education;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class CreateExperienceResource {
    @NotBlank
    @NotNull
    @Length(max = 120)
    private String title;

    @NotBlank
    @NotNull
    @Length(max = 1020)
    private String description;
}
