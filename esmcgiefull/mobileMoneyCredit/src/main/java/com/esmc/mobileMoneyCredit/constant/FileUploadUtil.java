package com.esmc.mobileMoneyCredit.constant;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
/**
 * @author Amemorte
 * @since 11/08/2022
 */
public class FileUploadUtil {
    public static  void  saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws Exception{
        Path uploadPath = Paths.get(uploadDir);

        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream=multipartFile.getInputStream()){
            Path filePath=uploadPath.resolve(fileName);
            Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IIOException ioe){
            throw  new IOException("l'image n'est pas en registrer dans le dossiers: "+fileName,ioe);
        }
    }


    public static  void  saveFileWithoutUsingMultipart(String uploadDir, String fileName, File file) throws Exception{
        Path uploadPath = Paths.get(uploadDir);

        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream=new FileInputStream(file)){
            Path filePath=uploadPath.resolve(fileName);
            Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IIOException ioe){
            throw  new IOException("l'image n'est pas en registrer dans le dossiers: "+fileName,ioe);
        }
    }
}
