package com.jongsik2.training.gymate.security.oauth2;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if ("google".equalsIgnoreCase(registrationId)) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if ("naver".equalsIgnoreCase(registrationId)) {
            return new NaverOAuth2UserInfo(attributes);
        }
//        else if ("kakao".equalsIgnoreCase(registrationId)) {
//            return new KakaoOAuth2UserInfo(attributes);
//        }
        else {
            throw new IllegalArgumentException("지원하지 않는 OAuth2 제공자: " + registrationId);
        }
    }
}
