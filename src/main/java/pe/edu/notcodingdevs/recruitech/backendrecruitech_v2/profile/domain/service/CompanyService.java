package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.service;

import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Company;

import java.util.List;

public interface CompanyService {
    Company create(Long locationId, Company company);
    Company update(Long companyId, Company company);
    List<Company> getAll();
}
