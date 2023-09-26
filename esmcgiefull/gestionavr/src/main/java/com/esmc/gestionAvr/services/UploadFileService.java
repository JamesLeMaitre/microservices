package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.Avr;
import com.esmc.gestionAvr.entities.Media;
import com.esmc.gestionAvr.repositories.AvrRepository;
import com.esmc.gestionAvr.repositories.MediaRepository;
import com.esmc.gestionAvr.servicesInterfaces.UploadFileInterface;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.esmc.gestionAvr.constant.javaConst.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Slf4j
@Service
public class UploadFileService implements UploadFileInterface {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private AvrRepository avrRepository;

    @Override
    public void save(MultipartFile[] files, Long idAvr) throws IOException {
        Avr avr = avrRepository.findById(idAvr).orElse(null);
        for (MultipartFile file : files){
            String fileData =file.getOriginalFilename();
            Path userPath = Paths.get(USER_FOLDER ).toAbsolutePath().normalize();
            Files.copy(file.getInputStream(), userPath.resolve(fileData), REPLACE_EXISTING);
            LOGGER.info("file uploaded "+fileData);
            Media media = new Media();
            media.setAvr(avr);
            media.setPhoto(fileData);
            mediaRepository.save(media);
        }
    }

    @Override
    public void deleteExitFile(String photoUrl) throws IOException {
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path(USER_IMAGE_PATH).toUriString();
        Path userPath = Paths.get(AVR_FOLDER).toAbsolutePath().normalize();
        String filename = photoUrl.replaceAll(baseUrl, "");
        Path path = Paths.get(userPath + "/" + filename);
        Files.deleteIfExists(path);
    }

/*
    @Override
    public String uploadFiles(String base64, String filename) throws IOException {
        // If you are using Java 8 or above
       // byte[] decodedFile = Base64.getDecoder().decode(base64.getBytes(StandardCharsets.UTF_8));

      // byte[] decodedFile = Base64.getMimeDecoder().decode(base64.getBytes(StandardCharsets.UTF_8));
//        InputStream inputStream  = new ByteArrayInputStream(decodedFile);

        byte[] decodedFile = Base64.getDecoder().decode(base64.getBytes(StandardCharsets.UTF_8));

        Path userPath = Paths.get(USER_FOLDER + filename).toAbsolutePath().normalize();
        if (!Files.exists(userPath)) {
            Files.createDirectories(userPath);
            log.info("Chemin à été créer: " + userPath);
        }
        String fileData = filename + "." + "png"
        Files.deleteIfExists(Paths.get(userPath + filename + "." + "png"));
        Files.write( userPath.resolve(filename + "." + "png"), decodedFile);
//        log.info("file {}",is);
//        Files.copy(file.getInputStream(), userPath.resolve(filename + "." + "jpg"), REPLACE_EXISTING);
//        log.info("{}", file);
//        try (OutputStream stream = new FileOutputStream(userPath.resolve(filename + "." + "jpg").toString())) {
//            stream.write(decodedFile);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//        InputStream is = new ByteArrayInputStream(decodedFile);
//        log.info("file {}",is);
//        IOUtils.copy(is, response.getOutputStream());
//        response.flushBuffer();
        return getImageUrl(filename);
    }


    private String getImageUrl(String fileName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(USER_IMAGE_PATH + fileName + "" + fileName + "." + "jpg").toUriString();
    }


*/
}
