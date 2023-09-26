package com.esmc.mobileMoneyCredit.controller;

import com.esmc.mobileMoneyCredit.entities.MediaTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static constants.javaConst.USER_FOLDER;

@RestController
@RequestMapping("api/PdfDownloader/")
public class DownloadPdfController {
    private static final String DEFAULT_FILE_NAME = "java-tutorial.pdf";

    private static final String DIRECTORY = "C:/PDF";

    @Autowired
    private ServletContext servletContext;

    // http://localhost:8080/download1?fileName=abc.zip
    // Using ResponseEntity<InputStreamResource>
    @RequestMapping("/download/{fileName}")
    public ResponseEntity<InputStreamResource> downloadFile1(@PathVariable String fileName) throws FileNotFoundException {

        String fileNames = fileName + ".pdf";
        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileNames);
        System.out.println("fileName: " + fileNames);
        System.out.println("mediaType: " + mediaType);

        //Remplacer DIRECTORY par USER_PDF_FOLDER lors du déployement
        File file = new File(DIRECTORY + "/" + fileNames);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(mediaType)
                // Contet-Length
                .contentLength(file.length()) //
                .body(resource);
    }
}
