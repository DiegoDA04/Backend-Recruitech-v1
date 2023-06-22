package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.api.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.domain.service.JobService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.mapping.JobMapper;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.resource.JobResource;

@RestController
@RequestMapping("api/v1/jobs")
@Tag(name = "Jobs", description = "Jobs Information")
public class JobsController {
    private final JobService jobService;
    private final JobMapper jobMapper;

    public JobsController(JobService jobService, JobMapper jobMapper) {
        this.jobService = jobService;
        this.jobMapper = jobMapper;
    }

    @RequestMapping
    @Operation(summary = "Get All Jobs")
    public ResponseEntity<Page<JobResource>> getAllJobs(Pageable pageable) {
        return new ResponseEntity<>(jobMapper.modelListPage(jobService.getAll(),pageable), HttpStatus.OK);
    }

    @RequestMapping("{jobId}")
    @Operation(summary = "Get Job by Id")
    public ResponseEntity<JobResource> getJobById(@PathVariable Long jobId) {
        return new ResponseEntity<>(jobMapper.toResource(jobService.getById(jobId)), HttpStatus.OK);
    }
}
