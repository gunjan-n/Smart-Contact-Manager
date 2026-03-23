package com.scm.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {

    public static String getEmailOfLoggedInUser(Authentication authentication){

        // AuthenticationPrincipal principal = (AuthenticationPrincipal) authentication.getPrincipal();

        if (authentication instanceof OAuth2AccessToken) {

            var aOAuth2AuthenticationToken =(OAuth2AuthenticationToken) authentication;
            var clientId = aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

            var oAuth2User = (OAuth2User) authentication.getPrincipal();
            var username="";

            if (clientId.equalsIgnoreCase("google")) {
                System.out.println("getting email from google");
                
                username = oAuth2User.getAttribute("email").toString();
            }

            return username;
            
        }  else {
                System.out.println("getting data from from local database");
                return authentication.getName();
            }

    }

}
