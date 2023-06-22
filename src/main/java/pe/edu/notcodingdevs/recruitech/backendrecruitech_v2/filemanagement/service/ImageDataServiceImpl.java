package pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.filemanagement.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.filemanagement.domain.model.entity.ImageData;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.filemanagement.domain.persistence.ImageDataRepository;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.filemanagement.domain.service.ImageDataService;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.filemanagement.resource.ImageResource;
import pe.edu.notcodingdevs.recruitech.backendrecruitech_v2.filemanagement.utils.ImageUtils;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageDataServiceImpl implements ImageDataService {

    private static final String PATH = "http://www.localhost:8080/api/v1/files/images/";
    private final ImageDataRepository imageDataRepository;


    public ImageDataServiceImpl(ImageDataRepository imageDataRepository) {
        this.imageDataRepository = imageDataRepository;
    }

    @Override
    public ImageResource uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = imageDataRepository.save(new ImageData()
                .withName(file.getOriginalFilename())
                .withType(file.getContentType())
                .withImageData(ImageUtils.compressImage(file.getBytes()))
        );

        if(imageData != null) {
            return new ImageResource()
                    .withSuccess(true)
                    .withMessage("file uploaded successfully: " + file.getOriginalFilename())
                    .withPath(PATH + file.getOriginalFilename());
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
