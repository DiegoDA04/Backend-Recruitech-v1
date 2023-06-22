package pe.edu.notcodingdevs.recruitech.backendrecruitech.profile.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.profile.domain.model.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    boolean existsByName(String name);

    Country findByName(String name);
}