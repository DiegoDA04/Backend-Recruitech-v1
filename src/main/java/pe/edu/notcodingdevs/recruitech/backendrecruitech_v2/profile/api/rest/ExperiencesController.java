package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.api.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.service.ExperienceService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.mapping.ExperienceMapper;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.education.CreateExperienceResource;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.education.ExperienceResource;

@RestController
@RequestMapping("api/v1/experiences")
@Tag(name = "Experiences", description = "Experience Information")
public class ExperiencesController {
    private final ExperienceService experienceService;
    private final ExperienceMapper experienceMapper;

    public ExperiencesController(ExperienceService experienceService, ExperienceMapper experienceMapper) {
        this.experienceService = experienceService;
        this.experienceMapper = experienceMapper;
    }

    @PostMapping("{developerId}/list-experiences")
    public ResponseEntity<ExperienceResource> createEducation(@PathVariable Long developerId,  @RequestParam(name = "company-id") Long companyId, @RequestBody CreateExperienceResource resource) {
        return new ResponseEntity<>(experienceMapper.toResource(experienceService.create(developerId, companyId, experienceMapper.toModel(resource))), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ExperienceResource>> getAllEducations(Pageable pageable) {
        return new ResponseEntity<>(experienceMapper.modelListPage(experienceService.getAll(), pageable), HttpStatus.OK);
    }
}
