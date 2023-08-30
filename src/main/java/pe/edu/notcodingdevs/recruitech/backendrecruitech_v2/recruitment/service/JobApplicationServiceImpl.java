package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.service;

import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Developer;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.persistence.DeveloperRepository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.domain.model.entity.Job;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.domain.model.entity.JobApplication;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.domain.persistence.JobApplicationRepository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.domain.persistence.JobRepository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.domain.service.JobApplicationService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.shared.exception.ResourceNotFoundException;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.shared.exception.ResourceValidationException;

import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {
    private static final String ENTITY = "Job Application";
    private final JobApplicationRepository jobApplicationRepository;
    private final JobRepository jobRepository;
    private final DeveloperRepository developerRepository;

    private final Validator validator;

    public JobApplicationServiceImpl(JobApplicationRepository jobApplicationRepository, JobRepository jobRepository, DeveloperRepository developerRepository, Validator validator) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.jobRepository = jobRepository;
        this.developerRepository = developerRepository;
        this.validator = validator;
    }

    @Override
    public JobApplication create(Long developerId, Long jobId) {
        Developer developer = developerRepository.findById(developerId).orElseThrow(() -> new ResourceNotFoundException("Developer", developerId));
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new ResourceNotFoundException("Job", jobId));
        int applicants = job.getApplicants();

        job.setApplicants(applicants + 1);

        jobRepository.save(job);

        Optional<JobApplication> jobApplicationWithDeveloperAndJob = jobApplicationRepository.findByDeveloperIdAndJobId(developerId,jobId);

        if(jobApplicationWithDeveloperAndJob.isPresent())
            throw new ResourceValidationException(ENTITY, "A Job Application with the same Developer and Job already exists.");

        return jobApplicationRepository.save(new JobApplication()
                .withJob(job)
                .withDeveloper(developer)
                .withStatus("In Review"));
    }

    @Override
    public List<JobApplication> getAllByDeveloperId(Long developerId) {
        return jobApplicationRepository.findAllByDeveloperId(developerId);
    }

    @Override
    public List<JobApplication> getAll() {
        return jobApplicationRepository.findAll();
    }

    @Override
    public List<JobApplication> getAllNoDeveloperApplications() {
        return null;
    }

    @Override
    public JobApplication getById(Long id) {
        return jobApplicationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }
}
