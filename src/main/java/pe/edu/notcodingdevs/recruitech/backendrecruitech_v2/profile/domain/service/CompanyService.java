package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.service;

import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Company;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Developer;

import java.util.List;

public interface CompanyService {
    Company create(Long locationId, Company company);
    Company update(Long companyId, Company company);
    Company updateAbout(Long companyId, Company company);
    Company updateProfilePicture(Long companyId, String profilePicture);
    Company updateBackgroundPicture(Long companyId, String backgroundPicture);
    List<Company> getAll();
}
