package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.security.persistence;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.service.LocationService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.security.domain.service.RoleService;

@Component
public class DatabaseSeedingConfig {
    private final RoleService roleService;
    private final LocationService locationService;

    public DatabaseSeedingConfig(RoleService roleService, LocationService locationService) {
        this.roleService = roleService;
        this.locationService = locationService;
    }

    @EventListener
    public void onApplicationReady(ApplicationReadyEvent event) {
        roleService.seed();
        locationService.seed();
    }
}
