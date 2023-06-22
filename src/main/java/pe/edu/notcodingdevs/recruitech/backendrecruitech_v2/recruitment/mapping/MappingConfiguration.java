package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.domain.model.entity.JobApplication;

@Configuration("recruitmentMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public JobMapper jobMapper(){ return new JobMapper(); }

    @Bean
    public JobApplicationMapper jobApplicationMapper(){ return new JobApplicationMapper(); }
}
