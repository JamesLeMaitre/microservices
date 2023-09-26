package com.esmc.mobileMoneyCredit.Service;

/**
 * @author Amemorte
 * @since 11/08/2022
 */

import com.esmc.mobileMoneyCredit.constant.FileUploadUtil;
import com.esmc.mobileMoneyCredit.entities.Attachment;
import com.esmc.mobileMoneyCredit.repositories.AttachmentRepository;
import com.esmc.mobileMoneyCredit.serviceInterfaces.AttachmentService;
import com.esmc.mobileMoneyCredit.utiles.JavaUtiles;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@Transactional
@Slf4j
public class AttachmentServiceImpl implements AttachmentService {


    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private AttachmentRepository attachmentRepository;

    // utilisation des multipartfile pour enregistrez
    @Override
    public Attachment saveAttachment(MultipartFile file) throws Exception {
       String fileName = StringUtils.cleanPath(file.getOriginalFilename());
       try {
            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence "
                + fileName);
            }

            Attachment attachment
                    = new Attachment(fileName,
                    file.getContentType());
            return attachmentRepository.save(attachment);

       } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
       }
    }

    // utilisation des fichiers pour enregistrements
    @Override
    public Attachment saveAttachmentPassingFile(File file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getName());
        try {
            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence "
                        + fileName);
            }
            String fileType = Files.probeContentType(file.toPath());
            Attachment attachment
                    = new Attachment(fileName ,  fileType);
            return attachmentRepository.save(attachment);

        } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
        }
    }


    @Override
    public Attachment getAttachment(String fileId) throws Exception {
        return attachmentRepository
                .findById(fileId)
                .orElseThrow(
                        () -> new Exception("File not found with Id: " + fileId));
    }





    @Override
    public void renameFilesInFolder( ) {
        String folderPath = JavaUtiles.USER_PATH+"/user/images";
        String newNamePrefix = "1__M_Left_middle_finger";

            File folder = new File(folderPath);
            for (File file : Objects.requireNonNull(folder.listFiles())) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    String newFileName = newNamePrefix + fileName;

                    file.renameTo(new File(folderPath + "/" + newFileName));
                    log.info("{}",file);
                }

        };
    }

    @Override
    public String renameAndMoveFilesInFolder( String newNamePrefix) throws Exception {
        String folderPath = JavaUtiles.USER_PATH_FINGER ;

//        String newNamePrefix = "2__M_Right_middle_finger";

        //cette variable pour récupérer la première lettre d'une chaine
        String firstChar = newNamePrefix.substring(0, 1);


        // cette variable pour definir le nom du fichier image par defaut
            String fileNames = "fingerprint.bmp";

        // cette variable fichier pour vérifier si le fichier image est contenu dans le chemin du fichier de départ
        File folderVariableOfChecking = new File(folderPath + fileNames);

        File folder = new File(folderPath);
        File dest = new File(JavaUtiles.USER_PATH_FINGER_DESTINATION_ONLINE  + firstChar);



        if(folderVariableOfChecking.exists()) {
            System.out.println("The file exists");

//            if(!folderVariableOfChecking.exists()){
////                String path = "/path/to/folder";
////                String fileName = "myimage.png";
//
//                // Create a file object in the specified folder
////                File file = new File(path + "/" + fileName);
//
//                // Create the image
//                folderVariableOfChecking.mkdirs();
//                try {
//                    BufferedImage image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
//                    ImageIO.write(image, "bmp", folderVariableOfChecking);
//                    System.out.println("Image created: " + folderVariableOfChecking.getName());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }


            if (!dest.exists()) {
                dest.mkdirs();
            }
            for (File file : folder.listFiles()) {
                if (file.isFile()) {

                    // we get the name of the file
                    String fileName = file.getName();
                    // then we get the file  extension and we add "."
                    String extension = FilenameUtils.getExtension(fileName);
                    String newFileName = newNamePrefix + "." + extension;
                    File newFile = new File(dest + "/" + newFileName);

//                    RestTemplate restTemplate = new RestTemplate();
//                    MultiValueMap<String,Object> body = new LinkedMultiValueMap<>();
//                    body.add("file", newFile);
//                    HttpHeaders headers = new HttpHeaders();
//                    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//                    HttpEntity<MultiValueMap<String,Object>> request = new HttpEntity<>(body,headers);
//                    restTemplate.postForObject("/uploads/saveFile",request,String.class);

//                    saveFileBeforeSendToAnotherDestination2(file)

//                    try {
                        //                file.renameTo(newFile);
                         Files.copy(file.toPath(), newFile.toPath());
                        //String url = "52.6.235.199:8888/MMCREDIT-SERVICE/api/uploads/saveFileoffinger";
                        String url = "http://52.6.235.199:8888/MMCREDIT-SERVICE/api/uploads/saveFileoffinger";
//                        String url = "http://localhost:8888/MMCREDIT-SERVICE/api/uploads/saveFile";
                        RestTemplate restTemplate = new RestTemplate();
                        FileSystemResource resource = new FileSystemResource(newFile);
                        MultiValueMap<String,Object> body = new LinkedMultiValueMap<>();
                        body.add("file", resource);
                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
                        HttpEntity<MultiValueMap<String,Object>> request = new HttpEntity<>(body,headers);
                        String response = restTemplate.postForObject(url,request,String.class);
                        System.out.println(response);

//                        Files.copy(file.toPath(), newFile.toPath());

//                    }
//                    catch (IOException e) {
//                        e.printStackTrace();
//                    }
                    log.info("{}", file);
                }

            }

//            MultipartFile files =null;
//            saveFileBeforeSendToAnotherDestination2(files);
            return "success";
        }
        else {

            return null;
        }
    }


    public String saveFileBeforeSendToAnotherDestination2(File file) throws Exception {
        List<String> values = new ArrayList<>() ;

//        Attachment attachment = attachmentService.saveAttachmentPassingFile(file);
        String fileName1 = file.getName();

       String uploadDIr ="/tmp/files/" + file.getName();
//        String uploadDIr ="/tmp/files/" ;

        FileUploadUtil.saveFileWithoutUsingMultipart(uploadDIr, fileName1, file);
//        String downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/download/")
//                .path(attachment.getId())
//                .toUriString();
//        values.add(attachment.getId()+"");


        return "files uploaded";
//    return renderDataArray(true,values,"files uploaded");

    }


//    public String saveFileConvertControllerToAnSerice(MultipartFile files) throws Exception {
//        List<String> values = new ArrayList<>() ;
//
//        Attachment attachment = attachmentService.saveAttachment(files);
//        String fileName1 = attachment.getFileName();
//
//        String uploadDIr ="/temporal/filesFolder/" + attachment.getId();
//
//        FileUploadUtil.saveFile(uploadDIr, fileName1, files);
//        String downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/download/")
//                .path(attachment.getId())
//                .toUriString();
//        values.add(attachment.getId()+"");
//
//
//        return "files uploaded";
////        return renderDataArray(true,values,"files uploaded");
//
//    }





}
