package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.api.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.domain.service.JobApplicationService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.mapping.JobApplicationMapper;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.resource.CreateJobApplicationResource;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.resource.JobApplicationResource;

@RestController
@RequestMapping("api/v1/job-applications")
@Tag(name = "Job Applications", description = "Jobs Information")
public class JobApplicationsController {

    private final JobApplicationService jobApplicationService;
    private final JobApplicationMapper jobApplicationMapper;

    public JobApplicationsController(JobApplicationService jobApplicationService, JobApplicationMapper jobApplicationMapper) {
        this.jobApplicationService = jobApplicationService;
        this.jobApplicationMapper = jobApplicationMapper;
    }

    @PostMapping("{developerId}/apply/{jobId}")
    public ResponseEntity<JobApplicationResource> createJobApplication(@PathVariable Long developerId, @PathVariable Long jobId) {
        return new ResponseEntity<>(jobApplicationMapper.toResource(jobApplicationService.create(developerId,jobId)), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<JobApplicationResource>> createJobApplication(Pageable pageable) {
        return new ResponseEntity<>(jobApplicationMapper.modelListPage(jobApplicationService.getAll(), pageable), HttpStatus.OK);
    }

    @GetMapping("{developerId}")
    public ResponseEntity<Page<JobApplicationResource>> getAllJobsByDeveloper(@PathVariable Long developerId,Pageable pageable) {
        return new ResponseEntity<>(jobApplicationMapper.modelListPage(jobApplicationService.getAllByDeveloperId(developerId), pageable), HttpStatus.OK);
    }
}
