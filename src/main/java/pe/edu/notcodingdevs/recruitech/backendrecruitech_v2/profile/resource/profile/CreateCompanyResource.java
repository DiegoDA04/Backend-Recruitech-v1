package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.profile;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class CreateCompanyResource {
    @NotNull
    @NotBlank
    @Length(max = 50)
    private String name;

    @Length(max = 120)
    private String about;

    private String profilePicture;
    private String backgroundPicture;
}
