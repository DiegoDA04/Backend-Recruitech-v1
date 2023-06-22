package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Company;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Developer;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Education;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.persistence.CompanyRepository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.persistence.DeveloperRepository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.persistence.EducationRepository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.service.EducationService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.shared.exception.ResourceNotFoundException;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.shared.exception.ResourceValidationException;

import java.util.List;
import java.util.Set;

@Service
public class EducationServiceImpl implements EducationService {

    private static final String ENTITY = "Education";
    private final EducationRepository educationRepository;
    private final DeveloperRepository developerRepository;
    private final CompanyRepository companyRepository;
    private final Validator validator;

    public EducationServiceImpl(EducationRepository educationRepository, DeveloperRepository developerRepository, CompanyRepository companyRepository, Validator validator) {
        this.educationRepository = educationRepository;
        this.developerRepository = developerRepository;
        this.companyRepository = companyRepository;
        this.validator = validator;
    }

    @Override
    public Education create(Long developerId, Long companyId, Education education) {
        Set<ConstraintViolation<Education>> violations = validator.validate(education);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Company company = companyRepository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Company", companyId));
        Developer developer = developerRepository.findById(developerId).orElseThrow(() -> new ResourceNotFoundException("Developer", developerId));
        developer.getEducations().add(education);

        education.setCompany(company);
        education.setDeveloper(developer);

        return educationRepository.save(education);
    }

    @Override
    public List<Education> getAll() {
        return educationRepository.findAll();
    }
}
