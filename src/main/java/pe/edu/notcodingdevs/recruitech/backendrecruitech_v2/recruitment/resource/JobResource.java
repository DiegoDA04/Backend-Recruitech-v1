package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.resource;

import lombok.*;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.profile.CompanyResource;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class JobResource {

    private Long id;
    private String title;
    private String description;
    private String type;
    private String remuneration;
    private CompanyResource company;
}
