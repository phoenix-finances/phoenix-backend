package io.omni.financia.config;

import io.omni.financia.security.JwtHelper;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpUtils {
    public static final String USER_TOKEN_COOKIE_NAME = "user_token";

    public static void addUserTokenCookie(HttpServletResponse response, String token){
        // https://dzone.com/articles/how-to-use-cookies-in-spring-boot
        // use response.addCookie()
        Cookie cookie = createUserTokenCookie(token);
        response.addCookie(cookie);
    }

    public static Cookie createUserTokenCookie(String token){
        Cookie cookie = new Cookie(USER_TOKEN_COOKIE_NAME, token);
        cookie.setMaxAge(JwtHelper.JWT_TOKEN_VALIDITY);
        cookie.setPath("/");
        return cookie;
    }

    public static Optional<String> getUserTokenCookie(HttpServletRequest request){
        // the request.getCookies() throws null pointer
        // Cannot read the array length because "<local5>" is null
        if (request.getCookies() != null)
            for (Cookie cookie : request.getCookies()){
                // logger.debug("Cookie={}", cookie);
                if (USER_TOKEN_COOKIE_NAME.equals(cookie.getName())){
                    // Try again with the cookie value
                    return Optional.of(cookie.getValue());
                }
            }
        return Optional.empty();
    }

    // Populated by Nginx
    public static final String HEADER_X_REAL_IP = "x-real-ip";

    // When Cloudflare is used, Cloudflare appends this header
    public static final String HEADER_X_FORWARDED_FOR = "x-forwarded-for";

    // Other Cloudflare specific headers
    public static final String HEADER_CF_CONNECTING_IP = "cf-connecting-ip";
    public static final String HEADER_CF_IP_COUNTRY = "cf-ipcountry";

    public static String getIP(HttpServletRequest request){
        return request.getHeader(HEADER_X_FORWARDED_FOR);
    }

    public static String getIpCountry(HttpServletRequest request){
        return request.getHeader(HEADER_CF_IP_COUNTRY);
    }

}
