package pe.edu.notcodingdevs.recruitech.backendrecruitech.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.security.domain.model.entity.Role;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.security.domain.model.entity.User;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.security.domain.model.enumeration.Roles;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.security.domain.persistence.RoleRepository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.security.domain.persistence.UserRepository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.security.domain.service.UserService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.security.domain.service.communication.request.LoginRequest;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.security.domain.service.communication.request.RegisterRequest;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.security.domain.service.communication.response.LoginResponse;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.security.domain.service.communication.response.RegisterResponse;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.security.middleware.JwtHandler;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.security.middleware.UserDetailsImpl;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.security.resource.AuthenticateResource;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.security.resource.UserResource;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.shared.exception.ResourceNotFoundException;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.shared.mapping.EnhancedModelMapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private  JwtHandler jwtHandler;
    @Autowired
    private  PasswordEncoder encoder;
    @Autowired
    private  EnhancedModelMapper mapper;

    @Override
    public ResponseEntity<?> authenticate(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String username = ((UserDetailsImpl) authentication.getPrincipal()).getUsername();

            String token = jwtHandler.generateTokenFromUsername(username);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());
            AuthenticateResource resource = mapper.map(userDetails, AuthenticateResource.class);
            resource.setUsername(username.split("@")[0]);
            resource.setToken(token);
            resource.setRoles(roles);
            LoginResponse response = new LoginResponse(resource);
            return ResponseEntity.ok(response.getResource());
        } catch (Exception e) {
            LoginResponse response = new LoginResponse(
                    String.format("An error occurred while authenticating: %s", e.getMessage()));
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> register(RegisterRequest request) {

        if(userRepository.existsByEmail(request.getEmail())) {
            RegisterResponse response = new RegisterResponse("Email is already used.");
            return ResponseEntity.badRequest().body(response.getMessage());
        }

        try {
            Set<String> rolesStringSet = request.getRoles();
            Set<Role> roles = new HashSet<>();

            if(rolesStringSet == null) {
                roleRepository.findByName(Roles.ROLE_USER)
                        .map(roles::add)
                        .orElseThrow(() -> new RuntimeException("Role not found"));
            } else {
                rolesStringSet.forEach(roleString ->
                        roleRepository.findByName(Roles.valueOf(roleString))
                                .map(roles::add)
                                .orElseThrow(() -> new RuntimeException("Role not found")));
            }

            String username = request.getEmail().split("@")[0];

            User user = new User()
                    .withUsername(username)
                    .withEmail(request.getEmail())
                    .withPassword(encoder.encode(request.getPassword()))
                    .withRoles(roles);

            userRepository.save(user);
            UserResource userResource = mapper.map(user, UserResource.class);
            RegisterResponse response = new RegisterResponse(userResource);
            response.setMessage("The user has been created successfully");

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            RegisterResponse response = new RegisterResponse(e.getMessage());
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> logout() {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with email: %s", username)));

        return UserDetailsImpl.build(user);
    }
}
