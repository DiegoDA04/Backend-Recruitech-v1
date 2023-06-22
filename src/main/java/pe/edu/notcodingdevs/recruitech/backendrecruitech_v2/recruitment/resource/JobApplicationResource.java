package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.recruitment.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class JobApplicationResource {
    private Long id;
    private String status;
    private JobResource job;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date applyDate;
}
