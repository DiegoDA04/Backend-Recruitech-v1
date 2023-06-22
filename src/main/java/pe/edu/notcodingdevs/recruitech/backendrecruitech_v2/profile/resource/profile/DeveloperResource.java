package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.profile;

import lombok.*;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.education.EducationResource;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.education.ExperienceResource;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.location.LocationResource;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class DeveloperResource {
    private Long id;
    private String firstName;
    private String lastName;
    private String about;
    private String birthDate;
    private String gender;
    private String photoUrl;
    private LocationResource location;
    private Set<EducationResource> educations;
    private Set<ExperienceResource> experiences;
}
