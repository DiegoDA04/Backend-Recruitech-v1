package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.service;

import org.springframework.stereotype.Service;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Developer;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Location;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.persistence.DeveloperRepository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.persistence.LocationRepository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.service.DeveloperService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.security.domain.model.entity.User;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.shared.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    private static final String ENTITY = "Developer";
    private final DeveloperRepository developerRepository;
    private final LocationRepository locationRepository;

    public DeveloperServiceImpl(DeveloperRepository developerRepository, LocationRepository locationRepository) {
        this.developerRepository = developerRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public Developer create(Developer developer, User user, Location location) {
        developer.setLocation(location);
        developer.setUser(user);
        if(developer.getPhotoUrl().isBlank() || developer.getPhotoUrl().isEmpty())
                developer.setPhotoUrl("http://localhost:8080/api/v1/files/images/default_profile_png");

        return developerRepository.save(developer);
    }

    @Override
    public List<Developer> getAll() {
        return developerRepository.findAll();
    }

    @Override
    public Developer getById(Long developerId) {
        return developerRepository.findById(developerId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, developerId));
    }
}
