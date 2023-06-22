package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.education;

import lombok.*;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.profile.CompanyResource;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class ExperienceResource {
    private Long id;
    private String title;
    private CompanyResource company;
}
