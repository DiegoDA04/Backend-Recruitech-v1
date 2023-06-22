package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Developer;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
}
