package com.ggomzirak.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class User extends BaseEntity {

	String userId;
	String password;
	String email;
	String name;
	String image;
	
	@Column(columnDefinition = "String default 1")
	String level;
	
    @Column(nullable=false, insertable=false,columnDefinition = "integer default 0")
    Integer points;
	
//	@Enumerated(EnumType.STRING)
//	private Role role;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salt_id")
    private Salt salt;
	
	private String refreshtoken;
	
	public User() {}

	@Builder
    public User(String name, String email, String image, String level, int points){
        this.name = name;
        this.email = email;
        this.image = image;
        this.level = level;
        this.points = points;
    }

    public User(Long userId) {
    	this.id = userId;
	}
    
    public User(String userId) {
    	this.userId = userId;
    }

	public User update(String name, String image){
        this.name = name;
        this.image = image;

        return this;
    }

}
