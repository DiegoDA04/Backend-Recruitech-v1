package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.api.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.service.CompanyService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.mapping.CompanyMapper;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.profile.*;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.domain.service.JobService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.mapping.JobMapper;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.resource.CreateJobResource;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.resource.JobResource;

@RestController
@RequestMapping("api/v1/companies")
@Tag(name = "Companies", description = "Companies Information")
public class CompaniesController {
    private final CompanyService companyService;
    private final JobService jobService;
    private final CompanyMapper companyMapper;
    private final JobMapper jobMapper;

    public CompaniesController(CompanyService companyService, JobService jobService, CompanyMapper companyMapper, JobMapper jobMapper) {
        this.companyService = companyService;
        this.jobService = jobService;
        this.companyMapper = companyMapper;
        this.jobMapper = jobMapper;
    }

    @GetMapping
    public ResponseEntity<Page<CompanyResource>> getAllCompanies(Pageable pageable) {
        return new ResponseEntity<>(companyMapper.modelListPage(companyService.getAll(), pageable), HttpStatus.OK);
    }

    @PostMapping("{companyId}/jobs")
    public ResponseEntity<JobResource> createJob(@PathVariable Long companyId, @RequestBody CreateJobResource resource) {
        return new ResponseEntity<>(jobMapper.toResource(jobService.create(companyId,jobMapper.toModel(resource))), HttpStatus.CREATED);
    }

    @GetMapping("{companyId}/jobs")
    public ResponseEntity<Page<JobResource>> getAllCompanyJobs(@PathVariable Long companyId, Pageable pageable) {
        return new ResponseEntity<>(jobMapper.modelListPage(jobService.getAllByCompanyId(companyId), pageable), HttpStatus.OK);
    }

    @PutMapping("{companyId}")
    public ResponseEntity<CompanyResource> updateCompany(@PathVariable Long companyId, @RequestBody CreateCompanyResource resource) {
        return new ResponseEntity<>(companyMapper.toResource(companyService.update(companyId, companyMapper.toModel(resource))), HttpStatus.OK);
    }

    @PatchMapping("{companyId}/background-picture")
    public ResponseEntity<CompanyResource> updateCompanyBackgroundPicture(@PathVariable Long developerId, @RequestParam(name = "url") String backgroundPicture){
        return new ResponseEntity<>(companyMapper.toResource(companyService.updateBackgroundPicture(developerId, backgroundPicture)), HttpStatus.OK);
    }
    @PatchMapping("{companyId}/profile-picture")
    public ResponseEntity<CompanyResource> updateCompanyProfilePicture(@PathVariable Long developerId, @RequestParam(name = "url") String profilePicture){
        return new ResponseEntity<>(companyMapper.toResource(companyService.updateProfilePicture(developerId, profilePicture)), HttpStatus.OK);
    }

    @PatchMapping("{companyId}/about")
    public ResponseEntity<CompanyResource> updateAboutCompany(@PathVariable Long companyId, @RequestBody UpdateAboutCompanyResource resource) {
        return new ResponseEntity<>(companyMapper.toResource(companyService.updateAbout(companyId, companyMapper.toModel(resource))), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<CompanyResource> createCompany(@RequestParam(name = "location-id") Long locationId, @RequestBody CreateCompanyResource resource) {
        return new ResponseEntity<>(companyMapper.toResource(companyService.create(locationId,companyMapper.toModel(resource))), HttpStatus.CREATED);
    }
}
