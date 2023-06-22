package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.security.service;

import org.springframework.stereotype.Service;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.security.domain.model.entity.Role;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.security.domain.model.enumeration.Roles;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.security.domain.persistence.RoleRepository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.security.domain.service.RoleService;

import java.util.Arrays;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void seed() {
        Arrays.stream(Roles.values()).forEach(role -> {
            if (!roleRepository.existsByName(role)) {
                roleRepository.save(new Role().withName(role));
            }
        });
    }
}
