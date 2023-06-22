package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Company;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Developer;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Experience;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.persistence.CompanyRepository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.persistence.DeveloperRepository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.persistence.ExperienceRepository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.service.ExperienceService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.shared.exception.ResourceNotFoundException;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.shared.exception.ResourceValidationException;

import java.util.List;
import java.util.Set;

@Service
public class ExperienceServiceImpl implements ExperienceService {

    private static final String ENTITY = "Experience";
    private final ExperienceRepository experienceRepository;
    private final DeveloperRepository developerRepository;
    private final CompanyRepository companyRepository;
    private final Validator validator;

    public ExperienceServiceImpl(ExperienceRepository experienceRepository, DeveloperRepository developerRepository, CompanyRepository companyRepository, Validator validator) {
        this.experienceRepository = experienceRepository;
        this.developerRepository = developerRepository;
        this.companyRepository = companyRepository;
        this.validator = validator;
    }

    @Override
    public Experience create(Long developerId, Long companyId, Experience experience) {
        Set<ConstraintViolation<Experience>> violations = validator.validate(experience);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Company company = companyRepository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Company", companyId));
        Developer developer = developerRepository.findById(developerId).orElseThrow(() -> new ResourceNotFoundException("Developer", developerId));
        developer.getExperiences().add(experience);

        experience.setDeveloper(developer);
        experience.setCompany(company);

        return experienceRepository.save(experience);
    }

    @Override
    public List<Experience> getAll() {
        return experienceRepository.findAll();
    }
}
