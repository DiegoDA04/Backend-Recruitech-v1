package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.profile;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.location.CreateLocationResource;

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

    @Length(max = 240)
    private String about;

    private String profilePicture;

    private String backgroundPicture;

    private CreateLocationResource location;

    @Email
    @NotNull
    @NotBlank
    private String email;

    @NotBlank
    @NotNull
    private String password;
}
