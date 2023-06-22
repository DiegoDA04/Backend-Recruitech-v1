package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.api.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.service.DeveloperService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.mapping.DeveloperMapper;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.profile.CreateDeveloperResource;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.profile.DeveloperResource;

@RestController
@RequestMapping("api/v1/developers")
@Tag(name = "Developers", description = "Companies Information")
public class DevelopersController {
    private final DeveloperService developerService;
    private final DeveloperMapper developerMapper;

    public DevelopersController(DeveloperService developerService, DeveloperMapper developerMapper) {
        this.developerService = developerService;
        this.developerMapper = developerMapper;
    }


    @GetMapping
    public ResponseEntity<Page<DeveloperResource>> getAllDevelopers(Pageable pageable){
        return new ResponseEntity<>(developerMapper.modelListPage(developerService.getAll(), pageable), HttpStatus.OK);
    }

    @GetMapping("{developerId}")
    public ResponseEntity<DeveloperResource> getDeveloperById(@PathVariable Long  developerId){
        return new ResponseEntity<>(developerMapper.toResource(developerService.getById(developerId)), HttpStatus.OK);
    }
}
