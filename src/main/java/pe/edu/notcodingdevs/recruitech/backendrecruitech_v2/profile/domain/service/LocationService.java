package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.service;

import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Country;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Location;

import java.util.List;

public interface LocationService {
    void seed();
    List<Location> getAll();
    List<Location> getAllByCountryId(Long countryId);
    Location create(Long countryId, Location location);
    Location getByName(String name);
}
