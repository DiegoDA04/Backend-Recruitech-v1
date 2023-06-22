package pe.edu.notcodingdevs.recruitech.backendrecruitech.security.api.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.profile.domain.model.entity.Location;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.profile.domain.service.DeveloperService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.profile.domain.service.LocationService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.profile.mapping.DeveloperMapper;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.profile.resource.CreateDeveloperResource;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.security.domain.model.entity.User;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.security.domain.service.UserService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.security.domain.service.communication.request.LoginRequest;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.security.domain.service.communication.request.RegisterRequest;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication", description = "Users Authorization for JWT Token")
public class AuthController {
    private final UserService userService;

    private final DeveloperService developerService;

    private final LocationService locationService;

    private final DeveloperMapper mapper;

    public AuthController(UserService userService, DeveloperService developerService, LocationService locationService, DeveloperMapper mapper) {
        this.userService = userService;
        this.developerService = developerService;
        this.locationService = locationService;
        this.mapper = mapper;
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

        developerService.createDeveloper(mapper.toModel(resource), user, location);

        return response;
    }
}
