package pe.edu.notcodingdevs.recruitech.backendrecruitech.file_management.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.file_management.domain.model.entity.ImageData;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.file_management.domain.persistence.ImageDataRepository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.file_management.domain.service.ImageDataService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.file_management.utils.ImageUtils;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.shared.exception.ResourceNotFoundException;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageDataServiceImpl implements ImageDataService {

    private final ImageDataRepository imageDataRepository;

    public ImageDataServiceImpl(ImageDataRepository imageDataRepository) {
        this.imageDataRepository = imageDataRepository;
    }

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = imageDataRepository.save(new ImageData()
                .withName(file.getOriginalFilename())
                .withType(file.getContentType())
                .withImageData(ImageUtils.compressImage(file.getBytes()))
        );

        if(imageData != null) {
            return "fil uploaded successfully: " + file.getOriginalFilename();
        }

        return null;
    }

    @Override
    public byte[] downloadImage(String filename) {
        Optional<ImageData> dbImageData = imageDataRepository.findByName(filename);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());

        return images;
    }
}
