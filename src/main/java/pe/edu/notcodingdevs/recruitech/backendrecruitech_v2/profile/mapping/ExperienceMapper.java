package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Education;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Experience;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.education.CreateExperienceResource;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.education.ExperienceResource;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.shared.mapping.EnhancedModelMapper;

import java.util.List;

public class ExperienceMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public ExperienceResource toResource(Experience model) {
        return mapper.map(model, ExperienceResource.class);
    }

    public Experience toModel(CreateExperienceResource resource) {
        return mapper.map(resource, Experience.class);
    }

    public Page<ExperienceResource> modelListPage(List<Experience> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, ExperienceResource.class), pageable, modelList.size());
    }
}
