package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.profile;

import lombok.*;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Location;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.location.LocationResource;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class CompanyResource {
    private Long id;
    private String name;
    private String about;
    private LocationResource location;
}
