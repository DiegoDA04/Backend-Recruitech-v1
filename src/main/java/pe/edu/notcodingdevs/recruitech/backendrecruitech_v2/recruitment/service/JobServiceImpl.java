package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.service;

import org.springframework.stereotype.Service;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Company;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.persistence.CompanyRepository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.domain.model.entity.Job;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.domain.persistence.JobRepository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.domain.service.JobService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.shared.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private static final String ENTITY = "Job";
    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    public JobServiceImpl(JobRepository jobRepository, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public Job create(Long companyId, Job job) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Company", companyId));
        job.setCompany(company);
        job.setApplicants(0);
        job.setIsFeatured(false);

        return jobRepository.save(job);
    }

    @Override
    public Job getById(Long jobId) {
        return jobRepository.findById(jobId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, jobId));
    }

    @Override
    public List<Job> getAllRecommendedJobs(Integer nApplicants) {
        return jobRepository.findAllByApplicantsGreaterThan(nApplicants);
    }

    @Override
    public List<Job> getAllFeaturedJobs() {
        return jobRepository.findAllByIsFeaturedIsTrue();
    }

    @Override
    public Job updateJobFeaturedByTrue(Long jobId) {
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, jobId));
        job.setIsFeatured(true);
        job.setApplicants(0);

        return jobRepository.save(job);
    }

    @Override
    public List<Job> getAllByCompanyId(Long companyId) {
        return jobRepository.findAllByCompanyId(companyId);
    }

    @Override
    public List<Job> getAll() {
        return jobRepository.findAll();
    }
}
