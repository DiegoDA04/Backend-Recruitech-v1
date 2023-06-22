package pe.edu.notcodingdevs.recruitech.backendrecruitech.file_management.domain.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageDataService {

    String uploadImage(MultipartFile file) throws IOException;

    byte[] downloadImage(String filename);
}
