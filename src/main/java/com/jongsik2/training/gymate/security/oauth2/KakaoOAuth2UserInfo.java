package com.jongsik2.training.gymate.security.oauth2;

import java.util.HashMap;
import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {
    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getEmail() {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        if (kakaoAccount != null && kakaoAccount.containsKey("email")) {
            return (String) kakaoAccount.get("email");
        }
        return "KAKAO_" + getProviderId() + "@kakao.com"; // 이메일이 없을 경우 임의 생성
    }

    @Override
    public String getName() {
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
        return properties != null ? (String) properties.get("nickname") : "Unknown";
    }

    @Override
    public String getProviderId() {
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public Map<String, Object> getAttributes() {
        Map<String, Object> modifiedAttributes = new HashMap<>(attributes);
        if (!modifiedAttributes.containsKey("email")) {
            modifiedAttributes.put("email", getEmail()); // 이메일이 없으면 추가
        }
        return modifiedAttributes;
    }
}
