package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.filemanagement.resource;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class ImageResource {
    private boolean success;
    private String message;
    private String path;
}
