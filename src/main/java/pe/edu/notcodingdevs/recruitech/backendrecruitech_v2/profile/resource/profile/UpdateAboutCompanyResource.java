package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.profile;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UpdateAboutCompanyResource {

    @Length(max = 120)
    private String about;
}
