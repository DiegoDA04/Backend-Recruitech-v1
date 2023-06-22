package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.domain.model.entity.Job;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.resource.CreateJobResource;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.resource.JobResource;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.shared.mapping.EnhancedModelMapper;

import java.io.Serializable;
import java.util.List;

public class JobMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public JobResource toResource(Job model) {
        return mapper.map(model, JobResource.class);
    }

    public Job toModel(CreateJobResource resource) {
        return mapper.map(resource, Job.class);
    }

    public Page<JobResource> modelListPage(List<Job> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, JobResource.class), pageable, modelList.size());
    }
}
