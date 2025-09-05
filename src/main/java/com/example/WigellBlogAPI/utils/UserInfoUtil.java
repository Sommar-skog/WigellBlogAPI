package com.example.WigellBlogAPI.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;

public class UserInfoUtil {

    public static String getUserNameFromJwt(Jwt jwt){
        return (String) jwt.getClaim("preferred_username"); //preffered_username
    }

    public static String getEmailFromJwt(Jwt jwt){
        return (String) jwt.getClaim("email");
    }

    public static String getGivenNameFromJwt(Jwt jwt){
        return (String) jwt.getClaim("given_name");
    }

    public static String getFamilyNameFromJwt(Jwt jwt){
        return (String) jwt.getClaim("family_name");
    }

    public static boolean hasRoleFromJwt(Jwt jwt, String role){ //Hämtas från Authoritiez i Token (mappningen)
        return jwt.getClaimAsStringList("authorities").contains("ROLE_" +role);
    }

    public static String getUserIdFromJwt(Jwt jwt){
        return (String) jwt.getClaim("sub");
    }

    public static String getUserIdFromAuthentication(Authentication authentication){
        return authentication.getName();
    }

    public static boolean hasRoleFromAuthentication(Authentication authentication, String role){ //Hämtas från converten
        return authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_" + role));
    }


}
