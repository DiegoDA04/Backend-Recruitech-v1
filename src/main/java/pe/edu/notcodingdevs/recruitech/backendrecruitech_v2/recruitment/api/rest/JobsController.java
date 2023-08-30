package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.api.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    @Operation(summary = "Get All Jobs")
    public ResponseEntity<Page<JobResource>> getAllJobs(Pageable pageable) {
        return new ResponseEntity<>(jobMapper.modelListPage(jobService.getAll(),pageable), HttpStatus.OK);
    }

    @GetMapping("{jobId}")
    @Operation(summary = "Get Job by Id")
    public ResponseEntity<JobResource> getJobById(@PathVariable Long jobId) {
        return new ResponseEntity<>(jobMapper.toResource(jobService.getById(jobId)), HttpStatus.OK);
    }

    @GetMapping("/featured")
    @Operation(summary = "Get All Featured Jobs")
    public ResponseEntity<Page<JobResource>> getAllFeaturedJobs(Pageable pageable) {
        return new ResponseEntity<>(jobMapper.modelListPage(jobService.getAllFeaturedJobs(), pageable), HttpStatus.OK);
    }

    @GetMapping("/recommended")
    @Operation(summary = "Get All Featured Jobs")
    public ResponseEntity<Page<JobResource>> getAllRecommendedJobs(Pageable pageable) {
        return new ResponseEntity<>(jobMapper.modelListPage(jobService.getAllRecommendedJobs(2), pageable), HttpStatus.OK);
    }

    @PatchMapping("{jobId}/featured")
    @Operation(summary = "Update job to featured Job")
    public ResponseEntity<JobResource> updateJobByFeaturedJob(@PathVariable Long jobId, Pageable pageable) {
        return new ResponseEntity<>(jobMapper.toResource(jobService.updateJobFeaturedByTrue(jobId)), HttpStatus.OK);
    }
}
