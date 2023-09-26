package com.esmc.exception;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RestDefaultException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String errorMessage;

    private final Map<String, Collection<String>> headers;

    public RestDefaultException(String errorMessage) {
        this(errorMessage, new HashMap<String, Collection<String>>());
    }

    public RestDefaultException(String errorMessage, Map<String, Collection<String>> headers) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.headers = headers;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * FeignResponse Headers
     *
     * @return
     */
    public Map<String, Collection<String>> getHeaders() {
        return headers;
    }
}
