package com.esmc.gestionCertification.services;


import com.esmc.gestionCertification.entities.Attachment;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Amemorte
 * @since 11/08/2022
 */
public interface AttachmentService {
    Attachment saveAttachment(MultipartFile file) throws Exception;

    Attachment getAttachment(String fileId) throws Exception;
}
