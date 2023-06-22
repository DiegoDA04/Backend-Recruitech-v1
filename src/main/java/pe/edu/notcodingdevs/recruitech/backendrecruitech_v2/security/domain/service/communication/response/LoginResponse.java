package pe.edu.notcodingdevs.recruitech.backendrecruitech.security.domain.service.communication.response;

import pe.edu.notcodingdevs.recruitech.backendrecruitech.security.resource.AuthenticateResource;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.shared.domain.service.communication.BaseResponse;

public class LoginResponse extends BaseResponse<AuthenticateResource> {
    public LoginResponse(String message) {
        super(message);
    }

    public LoginResponse(AuthenticateResource resource) {
        super(resource);
    }
}
