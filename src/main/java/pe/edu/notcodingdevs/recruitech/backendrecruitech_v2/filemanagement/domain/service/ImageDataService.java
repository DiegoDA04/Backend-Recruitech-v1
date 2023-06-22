package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.filemanagement.domain.service;

import org.springframework.web.multipart.MultipartFile;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.filemanagement.resource.ImageResource;

import java.io.IOException;

public interface ImageDataService {

    ImageResource uploadImage(MultipartFile file) throws IOException;

    byte[] downloadImage(String filename);
}
