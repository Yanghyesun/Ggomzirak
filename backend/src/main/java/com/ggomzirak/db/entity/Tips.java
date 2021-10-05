package com.ggomzirak.db.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DynamicUpdate
public class Tips extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "userId", foreignKey=@ForeignKey(name = "tips_fk_user_id"))
    User user;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy.MM.dd HH:mm", timezone="Asia/Seoul")
	@Column(nullable=false, insertable=false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	Timestamp time;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy.MM.dd HH:mm", timezone="Asia/Seoul")
	@Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	Timestamp edit_time;
	
    String title;
    
    @Column(columnDefinition="TEXT")
    String contents;
    
    @Column(columnDefinition="TEXT")
    String thumbnail;

    @Column(columnDefinition="TEXT")
    String image;
    
    @Column(nullable=false, insertable=false,columnDefinition = "integer default 0")
    Integer likes;
    
    @Column(columnDefinition="TEXT")
    String tags;
}
