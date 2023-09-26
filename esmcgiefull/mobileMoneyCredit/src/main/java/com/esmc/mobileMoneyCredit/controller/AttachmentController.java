package com.esmc.mobileMoneyCredit.controller;

import com.esmc.mobileMoneyCredit.constant.FileUploadUtil;
import com.esmc.mobileMoneyCredit.entities.Attachment;
import com.esmc.mobileMoneyCredit.repositories.AttachmentRepository;
import com.esmc.mobileMoneyCredit.request.FileRenameRequest;
import com.esmc.mobileMoneyCredit.serviceInterfaces.AttachmentService;
import com.esmc.models.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utiles.DataFormatter;
import utiles.UseFullFunctions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


/**
 * @author Amemorte
 * @since 11/08/2022
 */
@RestController
@RequestMapping("api/uploads")
public class AttachmentController extends DataFormatter<String> {


//    @Autowired
//    private Path sourceFolder;
//
//    @Autowired
//    private Path targetFolder;
    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private AttachmentRepository attachmentRepository;
    public UseFullFunctions useFullFunctions = new UseFullFunctions();

    @GetMapping("/save")
    public String saveImageByName(@RequestParam("fileName") String fileName) throws Exception {
        Attachment attachment
                = new Attachment(fileName,
                "image/jpeg");
        Attachment savedAttachment = attachmentRepository.save(attachment);
        return savedAttachment.getId();
    }

    @PostMapping("/saveFile")
    public Formatter<List<String>> saveProduit(@RequestParam("file") MultipartFile[] files) throws Exception {
        List<String> values = new ArrayList<>() ;
        for (MultipartFile file : files) {
            Attachment attachment = attachmentService.saveAttachment(file);
            String fileName1 = attachment.getFileName();

            // String uploadDIr = "doc-pdf-certification/" + attachment.getId();

            String uploadDIr ="/tmp/files/" + attachment.getId();

            FileUploadUtil.saveFile(uploadDIr, fileName1, file);
            String downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/download/")
                    .path(attachment.getId())
                    .toUriString();
            values.add(attachment.getId()+"");
        }

        return renderDataArray(true,values,"files uploaded");
    }

    @PostMapping("/saveFileoffinger")
    public Formatter<List<String>> saveFingFile(@RequestParam("file") MultipartFile[] files) throws Exception {
        List<String> values = new ArrayList<>() ;
        for (MultipartFile file : files) {
            Attachment attachment = attachmentService.saveAttachment(file);
            String fileName1 = attachment.getFileName();
            String firstChar = fileName1.substring(0, 1);
            // String uploadDIr = "doc-pdf-certification/" + attachment.getId();

//            String firstChar = newNamePrefix.substring(0, 1);
            String uploadDIr ="/tmp/finger/" + firstChar ;
            File dir = new File(uploadDIr);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            FileUploadUtil.saveFile(uploadDIr, fileName1, file);
            String downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/download/")
                    .path(attachment.getId())
                    .toUriString();
            values.add(attachment.getId()+"");
        }

        return renderDataArray(true,values,"files uploaded");
    }

    @PostMapping("/saveFileService")
    public Formatter<List<String>> saveSpecificFile(@RequestParam("file") MultipartFile[] files) throws Exception {

        List<String> values = new ArrayList<>() ;
        for (MultipartFile file : files) {
            Attachment attachment = attachmentService.saveAttachment(file);
            String fileName1 = attachment.getFileName();

            // String uploadDIr = "doc-pdf-certification/" + attachment.getId();

            String uploadDIr ="/temporales/files/" ;
//            String uploadDIr ="/temporales/files/" ;

            FileUploadUtil.saveFile(uploadDIr, fileName1, file);
//            String downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
//                    .path("/download/")
//                    .path(attachment.getId())
//                    .toUriString();
//            values.add(attachment.getId()+"");
        }

        return renderDataArray(true,values,"files uploaded");
    }

    @PostMapping("/saveFilev2")
    public Formatter<List<String>> saveProduit(@RequestBody String files) throws Exception {
        List<String> values = new ArrayList<>();
        long currenttime =  System.nanoTime();
        String[] strings = files.split(",");
        String extension = Stream.of("data:image/jpeg;base64", "data:image/png;base64")
                .filter(strings[0]::startsWith)
                .map(s -> s.equals("data:image/jpeg;base64") ? "jpeg" : "png")
                .findFirst()
                .orElse("jpg");
        String fileimage = "pic_" + currenttime;

        Attachment attachment = new Attachment(fileimage + "." + extension, "image/jpeg");
        Attachment savedAttachment = attachmentRepository.save(attachment);

        String folder = savedAttachment.getId();

        String newPicture = useFullFunctions.getNewUploadImageByBase64(files, folder, fileimage);
        values.add(folder);
        System.out.println("========");
        System.out.println(newPicture);

        return renderDataArray(true, values, "files uploaded");
    }

    private static final String EXTERNAL_FILE_PATH = "/tmp/files";
//    private static final String EXTERNAL_FILE_PATH = "/tmp/files";

    @RequestMapping("/file/{fileId}")
    public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileId") String fileId) throws Exception {
        String fileName;
        Attachment attachment = attachmentService.getAttachment(fileId);
        fileName=attachment.getFileName();

        File file = new File(EXTERNAL_FILE_PATH +"/"+attachment.getId()+"/"+ fileName);
        if (file.exists()) {
            //get the mimetype
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) {
                //unknown mimetype so set the mimetype to application/octet-stream
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
            response.setContentLength((int) file.length());
            InputStream inputStream = new BufferedInputStream(Files.newInputStream(file.toPath()));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        }
    }

//    @PostMapping("/renameAndMoveFile")
//    public ResponseEntity<?> renameAndMoveFile(@RequestBody FileRenameRequest request) {
//        try {
//            Files.move(sourceFolder.resolve(request.getOldName()), sourceFolder.resolve(request.getNewName()));
//            Files.move(sourceFolder.resolve(request.getNewName()), targetFolder.resolve(request.getNewName()));
//            return ResponseEntity.ok().build();
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//}

//    @GetMapping("/getFileName")
//    public Object getFile() {
//        DataFormatter<Flux<String>> df = new DataFormatter<>();
//        try{
//            return df.renderData(true,attachmentService.getFileNames(),"Done");
//        }catch (Exception e){
//            return renderStringData(false, String.valueOf(e),"Error");
//        }
//    }

//    @PutMapping("/renameFile")
//    public Object renameFilesInFolder() {
//        DataFormatter<Mono<Boolean>> df = new DataFormatter<>();
//        try{
//            return df.renderData(true,attachmentService.renameFilesInFolder(),"Done");
//        }catch (Exception e){
//            return renderStringData(false, String.valueOf(e),"Not Done");
//        }
//    }

    @PutMapping("/renameFile")
    public void renameFilesInFolder(){
        attachmentService.renameFilesInFolder();
    }

    @PutMapping("/renameAndMove/{fileNameInput}")
    public Object renameAndMoveFilesInFolder(@PathVariable("fileNameInput") String fileNameInput) throws Exception {


            String response = attachmentService.renameAndMoveFilesInFolder(fileNameInput);
            if (response == null) {
                return renderStringData(false, "", "Failure : The file fingerprint.bmp does not exists on the specified folder");
            }
            else {
                return renderStringData(true, "", "Success : The file is create  in the specified folder ");
            }


    }



}
