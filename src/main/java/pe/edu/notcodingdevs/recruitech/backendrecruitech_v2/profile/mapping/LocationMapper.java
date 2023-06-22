package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Location;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.location.CreateLocationResource;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.location.LocationResource;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.shared.mapping.EnhancedModelMapper;

import java.io.Serializable;
import java.util.List;

public class LocationMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public LocationResource toResource(Location model) {
        return mapper.map(model, LocationResource.class);
    }

    public Location toModel(CreateLocationResource resource) {
        return mapper.map(resource, Location.class);
    }

    public Page<LocationResource> modelListPage(List<Location> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, LocationResource.class), pageable, modelList.size());
    }
}
