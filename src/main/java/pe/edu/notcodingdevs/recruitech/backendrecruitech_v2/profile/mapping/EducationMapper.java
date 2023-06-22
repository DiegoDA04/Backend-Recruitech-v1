package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Education;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.education.CreateEducationResource;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.education.EducationResource;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.shared.mapping.EnhancedModelMapper;

import java.io.Serializable;
import java.util.List;

public class EducationMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public EducationResource toResource(Education model) {
        return mapper.map(model, EducationResource.class);
    }

    public Education toModel(CreateEducationResource resource) {
        return mapper.map(resource, Education.class);
    }

    public Page<EducationResource> modelListPage(List<Education> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, EducationResource.class), pageable, modelList.size());
    }
}
