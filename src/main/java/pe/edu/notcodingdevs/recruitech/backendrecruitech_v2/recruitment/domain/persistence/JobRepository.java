package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.domain.model.entity.Job;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findAllByCompanyId(Long companyId);
    List<Job> findAllByIsFeaturedIsTrue();
    List<Job> findAllByApplicantsGreaterThan(Integer nApplications);
}
