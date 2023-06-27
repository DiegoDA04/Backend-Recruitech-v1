package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.api.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.service.DeveloperService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.mapping.DeveloperMapper;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.profile.DeveloperResource;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.profile.UpdateAboutDeveloperResource;

@RestController
@RequestMapping("api/v1/developers")
@Tag(name = "Developers", description = "Developers Information")
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

    @GetMapping("{userId}/profile")
    public ResponseEntity<DeveloperResource> getDeveloperByUserId(@PathVariable Long userId){
        return new ResponseEntity<>(developerMapper.toResource(developerService.getByUSerId(userId)), HttpStatus.OK);
    }

    @PatchMapping("{developerId}/background-picture")
    public ResponseEntity<DeveloperResource> updateDeveloperPhotoUrL(@PathVariable Long developerId, @RequestParam(name = "url") String backgroundPicture){
        return new ResponseEntity<>(developerMapper.toResource(developerService.updateBackgroundPicture(developerId, backgroundPicture)), HttpStatus.OK);
    }

    @PatchMapping("{developerId}/about")
    public ResponseEntity<DeveloperResource> updateDeveloperAbout(@PathVariable Long developerId, @RequestBody UpdateAboutDeveloperResource developerResource){
        return new ResponseEntity<>(developerMapper.toResource(developerService.updateAbout(developerId, developerMapper.toModel(developerResource))), HttpStatus.OK);
    }

    @PatchMapping("{developerId}/profile-picture")
    public ResponseEntity<DeveloperResource> updateDeveloperProfilePicture(@PathVariable Long developerId, @RequestParam(name = "url") String profilePicture){
        return new ResponseEntity<>(developerMapper.toResource(developerService.updateProfilePicture(developerId, profilePicture)), HttpStatus.OK);
    }
}
