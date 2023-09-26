package com.esmc.mobileMoneyCredit.serviceInterfaces;


import com.esmc.mobileMoneyCredit.entities.Attachment;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;

/**
 * @author Amemorte
 * @since 11/08/2022
 */
public interface AttachmentService {
    Attachment saveAttachment(MultipartFile file) throws Exception;
    Attachment saveAttachmentPassingFile(File file) throws Exception;

    Attachment getAttachment(String fileId) throws Exception;





    public void renameFilesInFolder();

    public String renameAndMoveFilesInFolder(String newNamePrefix) throws Exception;


}
