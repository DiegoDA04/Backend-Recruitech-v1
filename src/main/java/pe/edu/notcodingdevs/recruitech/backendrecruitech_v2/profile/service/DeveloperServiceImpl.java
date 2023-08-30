package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.service;

import jakarta.transaction.Transactional;
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
        developer.setAbout("");
        developer.setOccupation("Developer");
        developer.setProfilePicture("https://app-backend-recruitech-230629033501.azurewebsites.net/api/v1/files/images/default_profile.png");
        developer.setBackgroundPicture("https://app-backend-recruitech-230629033501.azurewebsites.net/api/v1/files/images/default_background.png");

        return developerRepository.save(developer);
    }

    @Override
    public Developer updateProfilePicture(Long developerId, String profilePicture) {
        return developerRepository.findById(developerId).map(existingDeveloper ->
                        developerRepository.save(existingDeveloper
                                .withProfilePicture(profilePicture)))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, developerId)
                );
    }

    @Override
    public Developer updateBackgroundPicture(Long developerId, String backgroundPicture) {
        return developerRepository.findById(developerId).map(existingDeveloper ->
                        developerRepository.save(existingDeveloper
                                .withBackgroundPicture(backgroundPicture)))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, developerId)
                );
    }

    @Override
    @Transactional
    public Developer updateAbout(Long developerId, Developer developer) {
        return developerRepository.findById(developerId).map(existingDeveloper ->
                developerRepository.save(existingDeveloper
                        .withAbout(developer.getAbout())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, developerId));
    }

    @Override
    public List<Developer> getAll() {
        return developerRepository.findAll();
    }

    @Override
    public Developer getById(Long developerId) {
        return developerRepository.findById(developerId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, developerId));
    }

    @Override
    public Developer getByUSerId(Long userId) {
        return developerRepository.findByUserId(userId);
    }
}
