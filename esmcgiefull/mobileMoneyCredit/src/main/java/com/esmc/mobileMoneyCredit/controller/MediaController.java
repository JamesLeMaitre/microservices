package com.esmc.mobileMoneyCredit.controller;

import com.esmc.mobileMoneyCredit.input.testInput;
import constants.javaConst;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/consume-file/")
public class MediaController {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @GetMapping(value = "demo" )
        public Object demo( @RequestBody testInput el) throws IOException {
        System.out.println("Query param");
        System.out.println(el);
        return el;
    }

    @GetMapping(value = "image/{filename}")
    public void getImage(HttpServletResponse response, @PathVariable String filename ) throws IOException {
        try {
            String fileData = javaConst.USER_FOLDER +filename;
            InputStream is = new FileInputStream(fileData);
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            IOUtils.copy(is,response.getOutputStream());
        }catch (Exception e){

        }

    }

    @GetMapping(value = "origin")
    public String getImageFolder(HttpServletResponse response )  {
       return javaConst.USER_FOLDER;

    }
}
