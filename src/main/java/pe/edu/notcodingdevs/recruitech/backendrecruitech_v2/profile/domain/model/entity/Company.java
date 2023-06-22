package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.domain.model.entity.Job;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotNull
    @NotBlank
    @Length(max = 50)
    private String name;

    @Length(max = 120)
    private String about;

    // RELATIONSHIPS

    // MANY TO ONE - LOCATIONS
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    // ONE TO MANY - EDUCATIONS
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, mappedBy = "company")
    @JsonIgnore
    private Set<Education> educations = new HashSet<>();

    // ONE TO MANY - JOBS
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, mappedBy = "company")
    @JsonIgnore
    private Set<Job> jobs = new HashSet<>();

    // ONE TO MANY - EXPERIENCES
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, mappedBy = "company")
    @JsonIgnore
    private Set<Experience> experiences = new HashSet<>();
}
