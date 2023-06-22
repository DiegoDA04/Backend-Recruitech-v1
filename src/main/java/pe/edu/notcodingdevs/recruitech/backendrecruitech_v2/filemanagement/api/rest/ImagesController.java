package pe.edu.notcodingdevs.recruitech.backendrecruitech.file_management.api.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.notcodingdevs.recruitech.backendrecruitech.file_management.domain.service.ImageDataService;

@RestController
@RequestMapping("api/v1/files/images")
@Tag(name = "Files")
public class ImagesController {

    private final ImageDataService imageDataService;

    public ImagesController(ImageDataService imageDataService) {
        this.imageDataService = imageDataService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam(name = "image")MultipartFile file) throws Exception {
        String uploadImage = imageDataService.uploadImage(file);

        return new ResponseEntity<>(uploadImage, HttpStatus.OK);
    }
    @GetMapping("{filename}")
    public ResponseEntity<?> downloadImage(@PathVariable String filename) {
        byte[] imageData = imageDataService.downloadImage(filename);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
