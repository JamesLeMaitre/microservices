package interfaces;

public interface UseFullFunctionsInterface {

    String  getNewUploadImageByBase64(String base64String, String folder, String fileimage) throws Exception;

    public String  getUploadImageByBase64(String base64String);
}
