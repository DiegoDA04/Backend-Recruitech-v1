package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.service;

import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Experience;

import java.util.List;

public interface ExperienceService {
    Experience create(Long developerId, Long companyId, Experience experience);
    List<Experience> getAll();
}
