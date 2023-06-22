package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.service;

import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Developer;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Location;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.security.domain.model.entity.User;

import java.util.List;

public interface DeveloperService {
    Developer create(Developer developer, User user, Location location);

    List<Developer> getAll();
    Developer getById(Long developerId);
}
