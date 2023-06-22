package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.service;

import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Education;

import java.util.List;

public interface EducationService {
    Education create(Long developerId, Long companyId, Education education);
    List<Education> getAll();
}
