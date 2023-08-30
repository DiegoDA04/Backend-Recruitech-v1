package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.resource;

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
public class CreateJobResource {
    @NotNull
    @NotBlank
    @Length(max = 80)
    private String title;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @NotBlank
    private String skillsDescription;

    @NotNull
    @NotBlank
    private String type;

    @NotNull
    @NotBlank
    private String remuneration;
}
