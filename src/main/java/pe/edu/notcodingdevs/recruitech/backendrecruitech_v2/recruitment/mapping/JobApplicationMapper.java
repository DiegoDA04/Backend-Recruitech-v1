package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.domain.model.entity.Job;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.domain.model.entity.JobApplication;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.resource.*;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.shared.mapping.EnhancedModelMapper;

import java.io.Serializable;
import java.util.List;

public class JobApplicationMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public JobApplicationResource toResource(JobApplication model) {
        return mapper.map(model, JobApplicationResource.class);
    }


    public JobApplication toModel(CreateJobApplicationResource resource) {
        return mapper.map(resource, JobApplication.class);
    }

    public Page<JobApplicationResource> modelListPage(List<JobApplication> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, JobApplicationResource.class), pageable, modelList.size());
    }


}
