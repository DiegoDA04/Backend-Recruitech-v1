package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.profile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UpdateCompanyResource {

    @NotNull
    @NotBlank
    @Length(max = 50)
    private String name;

    @Length(max = 120)
    private String about;
}
