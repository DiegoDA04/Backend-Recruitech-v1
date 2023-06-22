package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Developer;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.profile.CreateDeveloperResource;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.profile.DeveloperResource;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.shared.mapping.EnhancedModelMapper;

import java.io.Serializable;
import java.util.List;

public class DeveloperMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public DeveloperResource toResource(Developer model) {
        return mapper.map(model, DeveloperResource.class);
    }

    public Developer toModel(CreateDeveloperResource resource) {
        return mapper.map(resource, Developer.class);
    }

    public Page<DeveloperResource> modelListPage(List<Developer> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, DeveloperResource.class), pageable, modelList.size());
    }
}
