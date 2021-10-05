package com.ggomzirak.db.entity;


import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Likes extends BaseEntity {
	Long postId;//qna의 id, tips의 id,shorts의 id와 연결
	
	@ManyToOne
	@JoinColumn(name = "typeId", foreignKey=@ForeignKey(name = "likes_fk_type_id"))
	PostType postType;

	@ManyToOne
	@JoinColumn(name = "userId", foreignKey=@ForeignKey(name = "likes_fk_user_id"))
	User user;
}
