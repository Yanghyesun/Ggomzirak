package com.ggomzirak.dto;

import lombok.Getter;

import java.io.Serializable;

import com.ggomzirak.db.entity.User;

/**
 * 직렬화 기능을 가진 User클래스
 */
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String image;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.image = user.getImage();
    }
}
