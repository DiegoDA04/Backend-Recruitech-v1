package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Country;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.persistence.CountryRepository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.service.CountryService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.shared.exception.ResourceValidationException;

import java.util.Optional;
import java.util.Set;

@Service
public class CountryServiceImpl implements CountryService {

    private static final String ENTITY = "Country";
    private final CountryRepository countryRepository;

    private final Validator validator;

    public CountryServiceImpl(CountryRepository countryRepository, Validator validator) {
        this.countryRepository = countryRepository;
        this.validator = validator;
    }

    @Override
    public Country create(Country country) {
        Set<ConstraintViolation<Country>> violations = validator.validate(country);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Optional<Country> countryWithName = countryRepository.findByName(country.getName());

        if(countryWithName.isPresent())
            throw new ResourceValidationException(ENTITY, "An country with the same name already exists.");

        return countryRepository.save(country);
    }


}
