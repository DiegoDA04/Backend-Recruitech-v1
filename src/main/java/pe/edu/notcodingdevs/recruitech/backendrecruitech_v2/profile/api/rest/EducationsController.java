package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.api.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.service.EducationService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.mapping.EducationMapper;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.education.CreateEducationResource;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.education.EducationResource;

@RestController
@RequestMapping("api/v1/educations")
@Tag(name = "Educations", description = "Education Information")
public class EducationsController {

    private final EducationService educationService;
    private final EducationMapper educationMapper;

    public EducationsController(EducationService educationService, EducationMapper educationMapper) {
        this.educationService = educationService;
        this.educationMapper = educationMapper;
    }

    @PostMapping({"{developerId}/list-educations"})
    public ResponseEntity<EducationResource> createEducation(@PathVariable Long developerId, @RequestParam(name = "company-id") Long companyId, @RequestBody CreateEducationResource resource) {
        return new ResponseEntity<>(educationMapper.toResource(educationService.create(developerId ,companyId,educationMapper.toModel(resource))), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<EducationResource>> getAllEducations(Pageable pageable) {
        return new ResponseEntity<>(educationMapper.modelListPage(educationService.getAll(), pageable), HttpStatus.OK);
    }
}
