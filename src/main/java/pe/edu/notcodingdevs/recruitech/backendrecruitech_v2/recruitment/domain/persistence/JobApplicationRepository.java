package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.domain.model.entity.JobApplication;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    Optional<JobApplication> findByDeveloperIdAndJobId(Long developerId, Long jobId);
    List<JobApplication> findAllByDeveloperId(Long developerId);
}
