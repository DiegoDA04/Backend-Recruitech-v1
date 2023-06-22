package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.service;

import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.domain.model.entity.Job;

import java.util.List;

public interface JobService {
    Job create(Long companyId, Job job);
    List<Job> getAll();
}
