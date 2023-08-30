package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Company;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Location;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.persistence.CompanyRepository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.persistence.LocationRepository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.service.CompanyService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.security.domain.model.entity.User;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.shared.exception.ResourceNotFoundException;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.shared.exception.ResourceValidationException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CompanyServiceImpl implements CompanyService {
    private static final String ENTITY = "Company";
    private final CompanyRepository companyRepository;
    private final LocationRepository locationRepository;

    private final Validator validator;

    public CompanyServiceImpl(CompanyRepository companyRepository, LocationRepository locationRepository, Validator validator) {
        this.companyRepository = companyRepository;
        this.locationRepository = locationRepository;
        this.validator = validator;
    }
    @Override
    public Company create(User user, Location location, Company company) {
        company.setLocation(location);
        company.setUser(user);
        company.setProfilePicture("https://app-backend-recruitech-230629033501.azurewebsites.net/api/v1/files/images/default_profile.png");
        company.setBackgroundPicture("https://app-backend-recruitech-230629033501.azurewebsites.net/api/v1/files/images/default_background.png");

        return companyRepository.save(company);
    }

    @Override
    public Company update(Long companyId, Company company) {

        Set<ConstraintViolation<Company>> violations = validator.validate(company);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Optional<Company> companyWithName = companyRepository.findByName(company.getName());

        if(companyWithName.isPresent())
            throw new ResourceValidationException(ENTITY, "An country with the same name already exists.");

        return companyRepository.findById(companyId).map(existingCompany ->
                companyRepository.save(existingCompany
                        .withName(company.getName())
                        .withAbout(company.getAbout())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, companyId)
                );
    }

    @Override
    public Company updateAbout(Long companyId, Company company) {

        return companyRepository.findById(companyId).map(existingCompany ->
                        companyRepository.save(existingCompany
                                .withAbout(company.getAbout())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, companyId)
                );
    }

    @Override
    public Company updateProfilePicture(Long companyId, String profilePicture) {
        return companyRepository.findById(companyId).map(existingCompany ->
                        companyRepository.save(existingCompany
                                .withProfilePicture(profilePicture)))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, companyId)
                );
    }

    @Override
    public Company updateBackgroundPicture(Long companyId, String backgroundPicture) {
        return companyRepository.findById(companyId).map(existingCompany ->
                        companyRepository.save(existingCompany
                                .withBackgroundPicture(backgroundPicture)))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, companyId)
                );
    }

    @Override
    public List<Company> getAll() {
        return companyRepository.findAll();
    }
}
