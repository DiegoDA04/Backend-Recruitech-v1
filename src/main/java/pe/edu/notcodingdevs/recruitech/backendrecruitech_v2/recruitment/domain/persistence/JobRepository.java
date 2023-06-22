package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.domain.model.entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
}
