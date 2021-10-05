package com.ggomzirak.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Salt extends BaseEntity{

	@Column(nullable=false)
    private String salt;

    public Salt() {
    }

    public Salt(String salt) {
        this.salt = salt;
    }
}