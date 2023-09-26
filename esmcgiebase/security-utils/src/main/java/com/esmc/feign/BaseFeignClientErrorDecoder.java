package com.esmc.feign;

import com.esmc.exception.RestDefaultException;
import feign.Response;
import feign.codec.ErrorDecoder;
import feign.codec.StringDecoder;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseFeignClientErrorDecoder implements ErrorDecoder {

    private static final Logger LOGGER = Logger.getLogger(BaseFeignClientErrorDecoder.class.getSimpleName());
    private final StringDecoder stringDecoder = new StringDecoder();

    @Override
    public RestDefaultException decode(final String methodKey, Response response) {
        String message = "Null Response Body.";
        if (response.body() != null) {
            try {
                message = stringDecoder.decode(response, String.class).toString();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, methodKey + "Error Deserializing response body from failed feign request response.", e);
            }
        } else {
            message = "status= " + response.status() + ", reason= " + response.reason();
        }
        return new RestDefaultException(methodKey + ": " + message, response.headers());
    }
//
//    @Override
//    public Exception decode(String methodKey, Response response) {
//        if (response.status() >= 400 && response.status() <= 499) {
//            //This is the custom exception given
//            return new StashClientException(
//                    response.status(),
//                    response.reason()
//            );
//        }
//        if (response.status() >= 500 && response.status() <= 599) {
//            //This is the custom exception given
//            return new StashServerException(
//                    response.status(),
//                    response.reason()
//            );
//        }
//         / / Here is the other status code processing method
//        return errorStatus(methodKey, response);
//    }
}
