package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.api.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.service.CountryService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.domain.service.LocationService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.mapping.CountryMapper;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.mapping.LocationMapper;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.location.CountryResource;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.location.CreateCountryResource;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.location.CreateLocationResource;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.resource.location.LocationResource;

@RestController
@RequestMapping("api/v1/locations")
@Tag(name = "Locations", description = "Locations Information")
public class LocationsController {

    private final LocationService locationService;
    private final CountryService countryService;
    private final LocationMapper locationMapper;
    private final CountryMapper countryMapper;

    public LocationsController(LocationService locationService, CountryService countryService, LocationMapper locationMapper, CountryMapper countryMapper) {
        this.locationService = locationService;
        this.countryService = countryService;
        this.locationMapper = locationMapper;
        this.countryMapper = countryMapper;
    }

    @GetMapping
    public ResponseEntity<Page<LocationResource>> getAllLocations(Pageable pageable) {
        return new ResponseEntity<>(locationMapper.modelListPage(locationService.getAll(), pageable), HttpStatus.OK);
    }

    @PostMapping("{countryId}/locations")
    public ResponseEntity<LocationResource> createLocation(@PathVariable Long countryId, @RequestBody CreateLocationResource resource) {
        return new ResponseEntity<>(locationMapper
                .toResource(locationService
                        .create(countryId, locationMapper
                                .toModel(resource))), HttpStatus.CREATED);
    }

    @GetMapping("{countryId}/locations")
    public ResponseEntity<Page<LocationResource>> getAllLocationByCountryId(@PathVariable Long countryId, Pageable pageable) {
        return new ResponseEntity<>(locationMapper.modelListPage(locationService.getAllByCountryId(countryId), pageable), HttpStatus.OK);
    }

    @PostMapping("/countries")
    public ResponseEntity<CountryResource> createCountry(@RequestBody CreateCountryResource resource) {
        return new ResponseEntity<>(countryMapper
                .toResource(countryService
                        .create(countryMapper
                                .toModel(resource))), HttpStatus.CREATED);
    }

}
