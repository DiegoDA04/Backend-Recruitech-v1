package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Country;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Location;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.persistence.CountryRepository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.persistence.LocationRepository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.service.LocationService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.shared.exception.ResourceNotFoundException;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.shared.exception.ResourceValidationException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LocationServiceImpl implements LocationService {

    private static final String ENTITY = "Location";
    private final LocationRepository locationRepository;
    private final CountryRepository countryRepository;

    private final Validator validator;

    public LocationServiceImpl(LocationRepository locationRepository, CountryRepository countryRepository, Validator validator) {
        this.locationRepository = locationRepository;
        this.countryRepository = countryRepository;
        this.validator = validator;
    }

    @Override
    public void seed() {
        List<String> countries = List.of("Peru");

        List<String> cities = List.of("Lima","Ica","Tacna","Cajamarca","Cuzco","Ucayali","Ayacucho","Amazonas","Trujillo","Loreto","Arequipa");

        countries.stream().forEach(country -> {
            if(!countryRepository.existsByName(country))
                countryRepository.save(new Country().withName(country));
        });

        cities.stream().forEach(city -> {
            if(!locationRepository.existsByName(city))
                locationRepository.save(new Location()
                                .withLocationName(city + ", Peru")
                        .withCountry(countryRepository.findByName("Peru").orElseThrow(() -> new ResourceNotFoundException("Peru")))
                        .withName(city)
                );
        });
    }

    @Override
    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    @Override
    public List<Location> getAllByCountryId(Long countryId) {
        return locationRepository.findAllByCountryId(countryId);
    }

    @Override
    public Location create(Long countryId, Location location) {
        Set<ConstraintViolation<Location>> violations = validator.validate(location);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Optional<Location> locationWithName = locationRepository.findByName(location.getName());

        if(locationWithName.isPresent())
            throw new ResourceValidationException(ENTITY, "A Location with the same name already exists.");

        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new ResourceNotFoundException("Country", countryId));

        location.setCountry(country);
        location.setLocationName(location.getName()+ ", " + country.getName());

        return locationRepository.save(location);
    }


    @Override
    public Location getByName(String name) {
        return locationRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException(ENTITY));
    }
}
