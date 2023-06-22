package pe.edu.notcodingdevs.recruitech.backendrecruitech.security.domain.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.security.domain.model.entity.User;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.security.domain.service.communication.request.LoginRequest;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.security.domain.service.communication.request.RegisterRequest;

public interface UserService extends UserDetailsService {
    ResponseEntity<?> authenticate(LoginRequest request);
    ResponseEntity<?> register(RegisterRequest request);
    ResponseEntity<?> logout();
    User getByEmail(String email);
}
