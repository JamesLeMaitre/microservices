package com.esmc.mobileMoneyCredit.constant;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * @author Amemorte
 * @since 11/08/2022
 */

@Configuration
public class MvcConfig implements WebMvcConfigurer{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){

//        exposeDirectory("doc-pdf",registry);
       exposeDirectory(javaConst.USER_PDF_FOLDER +"doc-pdf",registry);

    }
    private  void  exposeDirectory(String dirName,ResourceHandlerRegistry registry){
        Path uploadDir = Paths.get(dirName);
        String uploadPath=uploadDir.toFile().getAbsolutePath();

        if(dirName.startsWith("../")) dirName=dirName.replace("../","");

        registry.addResourceHandler("/"+dirName+"/**").addResourceLocations("file:/"+uploadPath+"/");
    }


}
