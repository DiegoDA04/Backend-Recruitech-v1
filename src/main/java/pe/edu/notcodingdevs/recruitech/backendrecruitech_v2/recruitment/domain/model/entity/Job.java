package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Company;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Developer;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Education;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Experience;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Length(max = 80)
    private String title;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @NotBlank
    @Column(name = "skills_description")
    private String skillsDescription;

    @NotNull
    @NotBlank
    private String type;

    @NotNull
    @NotBlank
    private String remuneration;

    // RELATIONSHIPS

    // MANY TO ONE - COMPANY
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    // ONE TO MANY - JOB APPLICATION
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, mappedBy = "job")
    @JsonIgnore
    private Set<JobApplication> applications = new HashSet<>();
}
