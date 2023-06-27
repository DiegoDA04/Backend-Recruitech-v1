package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.domain.model.entity.JobApplication;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.security.domain.model.entity.User;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.shared.domain.model.AuditModel;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
@Table(name = "developers")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Length(max = 40)
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @NotNull
    @Length(max = 40)
    @Column(name = "last_name")
    private String lastName;

    @Length(max = 240)
    @NotNull
    private String about;

    private String occupation;

    @NotNull
    @NotBlank
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "birth_date")
    private String birthDate;

    @NotNull
    @NotBlank
    private String gender;

    @Column(name = "background_picture")
    private String backgroundPicture;

    @Column(name = "profile_picture")
    private String profilePicture;

    // RELATIONSHIPS

    // MANY TO ONE - LOCATIONS
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "location_id")
    private Location location;

    // ONE TO MANY - EDUCATIONS
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, mappedBy = "developer")
    @JsonIgnore
    private Set<Education> educations = new HashSet<>();

    // ONE TO MANY - EXPERIENCES
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, mappedBy = "developer")
    @JsonIgnore
    private Set<Experience> experiences = new HashSet<>();

    // ONE TO MANY - JOB APPLICATIONS
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, mappedBy = "developer")
    @JsonIgnore
    private Set<JobApplication> applications = new HashSet<>();

    // ONE TO ONE - USER
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
