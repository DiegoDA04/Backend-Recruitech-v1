package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.profile;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UpdateAboutDeveloperResource {
    @Length(max = 240)
    private String about;
}
