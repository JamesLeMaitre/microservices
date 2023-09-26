package com.esmc.gestionCertification.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadFileService {
    void save(MultipartFile[] file, Long idPostuler) throws IOException;
    public void deleteExitFile(String photoUrl) throws IOException;
    //

   // String save(MultipartFile file, String fileName) throws IOException;
    //String uploadFiles(String base64, String name) throws IOException;

    public  void uploadFile (MultipartFile file) throws IOException;
}
