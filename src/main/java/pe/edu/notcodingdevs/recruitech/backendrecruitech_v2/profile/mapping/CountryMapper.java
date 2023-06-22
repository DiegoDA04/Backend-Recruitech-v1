package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.model.entity.Country;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.location.CountryResource;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.location.CreateCountryResource;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.shared.mapping.EnhancedModelMapper;

import java.io.Serializable;
import java.util.List;

public class CountryMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public CountryResource toResource(Country model) {
        return mapper.map(model, CountryResource.class);
    }

    public Country toModel(CreateCountryResource resource) {
        return mapper.map(resource, Country.class);
    }

    public Page<CountryResource> modelListPage(List<Country> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, CountryResource.class), pageable, modelList.size());
    }
}
