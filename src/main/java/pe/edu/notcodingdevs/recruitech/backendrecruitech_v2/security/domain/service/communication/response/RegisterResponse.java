package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.security.domain.service.communication.response;

import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.security.resource.UserResource;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.shared.domain.service.communication.BaseResponse;

public class RegisterResponse extends BaseResponse<UserResource> {
    public RegisterResponse(String message) {
        super(message);
    }

    public RegisterResponse(UserResource resource) {
        super(resource);
    }
}
