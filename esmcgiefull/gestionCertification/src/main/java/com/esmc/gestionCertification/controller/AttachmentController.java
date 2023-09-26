package com.esmc.gestionCertification.controller;



import com.esmc.gestionCertification.ResponseData;
import com.esmc.gestionCertification.constant.FileUploadUtil;
import com.esmc.gestionCertification.entities.Attachment;
import com.esmc.gestionCertification.services.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static constants.javaConst.USER_PDF_FOLDER;

/**
 * @author Amemorte
 * @since 11/08/2022
 */
@RestController
@RequestMapping("api/uploads")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;




    @PostMapping("/saveFile")
    public ResponseData saveProduit(@RequestParam("file") MultipartFile file) throws Exception {

        Attachment attachment = null;
        String downloadURl = "";

        attachment = attachmentService.saveAttachment(file);

        String fileName1=attachment.getFileName();

       // "produit-image/"+id+"/" +photo1;

        String uploadDIr =USER_PDF_FOLDER +"/doc-pdf-certification/"+attachment.getId();

        FileUploadUtil.saveFile(uploadDIr,fileName1,file);


        downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(attachment.getId())
                .toUriString();

        return new ResponseData(attachment.getFileName(),
                downloadURl,
                file.getContentType(),
                file.getSize());
    }

    

//    @GetMapping("/download/{fileId}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
//        Attachment attachment = null;
//        attachment = attachmentService.getAttachment(fileId);
//        return  ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(attachment.getFileType()))
//                .header(HttpHeaders.CONTENT_DISPOSITION,
//                        "attachment; filename=\"" + attachment.getFileName()
//                + "\"")
//                .body(new ByteArrayResource(attachment.getData()));
//    }
    @GetMapping("/download/{fileId}")
    public String downloadloadFile(@PathVariable String fileId) throws Exception {
        Attachment attachment = null;
        attachment = attachmentService.getAttachment(fileId);
        return  "doc-pdf/"+attachment.getId()+"/"+attachment.getFileName();
    }
}
