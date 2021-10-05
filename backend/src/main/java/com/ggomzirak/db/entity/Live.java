package com.ggomzirak.db.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ggomzirak.dto.UserResponse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Live extends BaseEntity {
	
	@ManyToOne
    @JoinColumn(name="userId", foreignKey=@ForeignKey(name = "live_fk_user_id"))
    User user;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy.MM.dd HH:mm", timezone="Asia/Seoul")
	@Column(nullable=false, insertable=false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	Timestamp starttime;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy.MM.dd HH:mm", timezone="Asia/Seoul")
	@Column(nullable=true)
	LocalDateTime endtime;
	
	Integer type;
	
	@Column(columnDefinition="TEXT")
	String title;
	
	@Column(columnDefinition="TEXT")
	String contents;
	
	Integer viewers;
	Integer maxviewers;
	
	@Column(columnDefinition="TEXT", nullable = true)
    String thumbnail;
    
    @Column(columnDefinition="TEXT", nullable = true)
    String video;

	@Override
	public String toString() {
		return "Live [userId=" + user.getUserId() + ", starttime=" + starttime + ", endtime=" + endtime + ", type=" + type
				+ ", title=" + title + ", contents=" + contents + ", viewers=" + viewers + ", maxviewers=" + maxviewers
				+ ", thumbnail=" + thumbnail + ", video=" + video + "]";
	}
    
}
