package com.esmc.gestionAvr.servicesInterfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadFileInterface {
    void save(MultipartFile[] file, Long idAvr) throws IOException;
    public void deleteExitFile(String photoUrl) throws IOException;
    //String uploadFiles(String base64, String name) throws IOException;
}
