package com.esmc.gestionCertification.serviceImp;

/**
 * @author Amemorte
 * @since 11/08/2022
 */

import com.esmc.gestionCertification.entities.Attachment;
import com.esmc.gestionCertification.repository.AttachmentRepository;
import com.esmc.gestionCertification.services.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AttachmentServiceImpl implements AttachmentService {


    @Autowired
    private AttachmentRepository attachmentRepository;

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


    @Override
    public Attachment getAttachment(String fileId) throws Exception {
        return attachmentRepository
                .findById(fileId)
                .orElseThrow(
                        () -> new Exception("File not found with Id: " + fileId));
    }
}
