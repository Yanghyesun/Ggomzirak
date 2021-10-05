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
public class Ranking extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name = "userId", foreignKey=@ForeignKey(name = "rank_fk_user_id"))
	User user;
	
	int likes;
	int posts;
	int views;
	int ranking;
}
