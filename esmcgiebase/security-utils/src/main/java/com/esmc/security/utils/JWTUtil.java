package com.esmc.security.utils;

public class JWTUtil {

    public static final String SECRET = "fabio1234";
    public static final String AUTH_HEADER = "Authorization";
    public static final String AUTH_PREFIX = "Bearer ";
    public static final long EXPIRE_ACCESS_TOKEN = 1800 * 24 * 60 * 60 * 1000;
    public static final long EXPIRE_REFRESH_TOKEN = 1800 * 24 * 60 * 60 * 1000;
    public static final String ACCESS_TOKEN = "accessToken";
    public static final String REFRESH_TOKEN = "refreshToken";
}
