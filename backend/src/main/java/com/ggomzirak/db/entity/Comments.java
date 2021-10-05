package com.ggomzirak.db.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comments extends BaseEntity {
	Long postId;//qna의 id, tips의 id,shorts의 id와 연결
	
	@ManyToOne
	@JoinColumn(name = "typeId", foreignKey=@ForeignKey(name = "comments_fk_type_id"))
	PostType postType;

	@ManyToOne
	@JoinColumn(name = "userId", foreignKey=@ForeignKey(name = "comments_fk_user_id"))
	User user;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy.MM.dd HH:mm", timezone="Asia/Seoul")
	@Column(nullable=false, insertable=false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	Timestamp time;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy.MM.dd HH:mm", timezone="Asia/Seoul")
	@Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	Timestamp edit_time;
	
    @Column(columnDefinition="TEXT")
    String contents;
}
