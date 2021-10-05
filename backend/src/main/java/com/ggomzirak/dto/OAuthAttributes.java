//package com.ggomzirak.dto;
//
//import lombok.Builder;
//import lombok.Getter;
//
//import java.util.Map;
//
//import com.ggomzirak.db.entity.Role;
//import com.ggomzirak.db.entity.User;
//
//@Getter
//public class OAuthAttributes {
//    private Map<String, Object> attributes; // OAuth2 반환하는 유저 정보 Map
//    private String nameAttributeKey;
//    private String name;
//    private String email;
//    private String image;
//
//    @Builder
//    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String image) {
//        this.attributes = attributes;
//        this.nameAttributeKey = nameAttributeKey;
//        this.name = name;
//        this.email = email;
//        this.image = image;
//    }
//
//    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
//        // 여기서 네이버와 카카오 등 구분 (ofNaver, ofKakao)
//
//        return ofGoogle(userNameAttributeName, attributes);
//    }
//
//    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
//        return OAuthAttributes.builder()
//                .name((String) attributes.get("name"))
//                .email((String) attributes.get("email"))
//                .image((String) attributes.get("picture"))
//                .attributes(attributes)
//                .nameAttributeKey(userNameAttributeName)
//                .build();
//    }
//
//    public User toEntity(){
//        return User.builder()
//                .name(name)
//                .email(email)
//                .image(image)
//                .role(Role.GUEST) // 기본 권한 GUEST
//                .level("1")
//                .points(0)
//                .build();
//    }
//
//}
