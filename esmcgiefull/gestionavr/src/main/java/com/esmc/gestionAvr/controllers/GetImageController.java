package com.esmc.gestionAvr.controllers;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.esmc.gestionAvr.constant.javaConst.USER_FOLDER;
import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@CrossOrigin("*")
@Slf4j
@RestController
@RequestMapping(path = "api/image/")
public class GetImageController {

    @GetMapping(value = "name/{filename}")
    public void getImage(HttpServletResponse response, @PathVariable String filename ) throws IOException {
        String fileData =USER_FOLDER +filename;
        LOGGER.info(fileData);
        InputStream is = new FileInputStream(fileData);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(is,response.getOutputStream());
    }
}
