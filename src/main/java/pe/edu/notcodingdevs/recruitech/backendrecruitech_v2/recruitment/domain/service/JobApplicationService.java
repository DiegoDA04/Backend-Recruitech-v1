package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.domain.service;


import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.domain.model.entity.JobApplication;

import java.util.List;

public interface JobApplicationService {
    JobApplication create(Long developerId, Long jobId);
    List<JobApplication> getAllByDeveloperId(Long developerId);
    List<JobApplication> getAll();
    JobApplication getById(Long id);

}
