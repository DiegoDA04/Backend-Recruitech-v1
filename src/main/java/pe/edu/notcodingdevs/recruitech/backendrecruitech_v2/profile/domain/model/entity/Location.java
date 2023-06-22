package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotBlank
    @NotNull
    @Length(max = 40)
    private String name;

    @Column(name = "location_name")
    private String locationName;

    // ==== RELATIONSHIPS ===

    // MANY TO ONE - COUNTRY
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    // ONE TO MANY - COMPANY
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, mappedBy = "location")
    @JsonIgnore
    private Set<Company> companies = new HashSet<>();
}
