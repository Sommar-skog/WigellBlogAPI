package com.example.WigellBlogAPI.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public class UserInfoUtil {

    public static String getUsernameFromJwt(Jwt jwt){
        return jwt.getClaimAsString("preferred_username");
    }

    public static String getEmailFromJwt(Jwt jwt){
        return jwt.getClaimAsString("email");
    }

    public static String getGivenNameFromJwt(Jwt jwt){
        return jwt.getClaimAsString("given_name");
    }

    public static String getFamilyNameFromJwt(Jwt jwt){
        return jwt.getClaimAsString("family_name");
    }

    public static boolean hasRoleFromJwt(Jwt jwt, String role){ //Hämtas från Authorities i Token (mappningen)
        return jwt.getClaimAsStringList("authorities").contains("ROLE_" +role);
    }

    public static List<String> getRolesFromJwt(Jwt jwt){
        return jwt.getClaimAsStringList("authorities");
    }

    public static String getUserIdFromJwt(Jwt jwt){
        return jwt.getClaimAsString("sub");
    }

    public static String getUserIdFromAuthentication(Authentication authentication){
        return authentication.getName();
    }

    public static boolean hasRoleFromAuthentication(Authentication authentication, String role){ //Hämtas från convertern
        return authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_" + role));
    }

    public static List<String> getRolesFromAuthentication(Authentication authentication){
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
    }


}
