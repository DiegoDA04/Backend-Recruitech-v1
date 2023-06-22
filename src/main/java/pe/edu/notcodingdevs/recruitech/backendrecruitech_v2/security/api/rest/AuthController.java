package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.security.api.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Location;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.service.DeveloperService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.service.LocationService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.mapping.DeveloperMapper;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.profile.CreateDeveloperResource;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.security.domain.model.entity.User;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.security.domain.service.UserService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.security.domain.service.communication.request.LoginRequest;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.security.domain.service.communication.request.RegisterRequest;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication", description = "Users Authorization for JWT Token")
public class AuthController {
    private final UserService userService;
    private final LocationService locationService;
    private final DeveloperService developerService;
    private final DeveloperMapper developerMapper;

    public AuthController(UserService userService, LocationService locationService, DeveloperService developerService, DeveloperMapper developerMapper) {
        this.userService = userService;
        this.locationService = locationService;
        this.developerService = developerService;
        this.developerMapper = developerMapper;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request) {
        return userService.authenticate(request);
    }
    @PostMapping("/developer/sign-up")
    public ResponseEntity<?> registerDeveloper(@RequestBody CreateDeveloperResource resource) {
        RegisterRequest request = new RegisterRequest();
        request.setEmail(resource.getEmail());
        request.setPassword(resource.getPassword());
        ResponseEntity<?> response =  userService.register(request);

        Location location = locationService.getByName(resource.getLocation().getName());
        User user = userService.getByEmail(request.getEmail());

        developerService.create(developerMapper.toModel(resource), user, location);

        return response;
    }
}
