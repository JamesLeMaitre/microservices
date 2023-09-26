package utiles;

import constants.javaConst;
import interfaces.UseFullFunctionsInterface;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UseFullFunctions implements UseFullFunctionsInterface {


    //private static final String EXTERNAL_FILE_PATH = "doc-pdf-certification";
    private static final String EXTERNAL_FILE_PATH ="/tmp/files";


    @Override
    public String  getNewUploadImageByBase64(String base64String, String folder, String fileimage)throws Exception{
// tokenize the data
        String[] strings = base64String.split(",");
        String extension;
        switch (strings[0]) {//check image's extension
            case "data:image/jpeg;base64":
                extension = "jpeg";
                break;
            case "data:image/png;base64":
                extension = "png";
                break;
            default://should write cases for more images types
                extension = "jpg";
                break;
        }
        String uploadDir = EXTERNAL_FILE_PATH +"/"+folder;
        Path uploadPath = Paths.get(uploadDir);
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        String fileData = fileimage+"." +extension;
        //convert base64 string to binary data
        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
        String path = uploadPath+"/"+fileData;
        File file = new File(path);
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileData;
    }
    @Override
    public String  getUploadImageByBase64(String base64String){
        long currenttime =  System.nanoTime();
        String fileimage="pic_"+currenttime;
// tokenize the data
        String[] strings = base64String.split(",");
        String extension;
        switch (strings[0]) {//check image's extension
            case "data:image/jpeg;base64":
                extension = "jpeg";
                break;
            case "data:image/png;base64":
                extension = "png";
                break;
            default://should write cases for more images types
                extension = "jpg";
                break;
        }
        String fileData = fileimage+"." +extension;
        //convert base64 string to binary data
        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
        String path = javaConst.USER_FOLDER +fileData;
        File file = new File(path);
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileData;
    }
}
