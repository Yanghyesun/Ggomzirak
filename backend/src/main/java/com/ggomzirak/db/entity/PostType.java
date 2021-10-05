package com.ggomzirak.db.entity;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PostType extends BaseEntity{
	String type;
}
