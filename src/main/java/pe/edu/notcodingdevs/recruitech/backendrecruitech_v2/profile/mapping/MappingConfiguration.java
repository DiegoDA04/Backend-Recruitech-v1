package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.profile.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("profileMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public CountryMapper countryMapper() { return new CountryMapper(); }

    @Bean
    public LocationMapper locationMapper() { return new LocationMapper(); }

    @Bean
    public EducationMapper educationMapper(){ return new EducationMapper(); }

    @Bean
    public CompanyMapper companyMapper(){ return new CompanyMapper(); }

    @Bean
    public ExperienceMapper experienceMapper(){ return new ExperienceMapper(); }

    @Bean
    public DeveloperMapper developerMapper(){ return new DeveloperMapper(); }
}
