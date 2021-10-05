package com.ggomzirak.db.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ggomzirak.db.entity.QLikes;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class LikesRepositorySupport {

	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QLikes qLikes  = QLikes.likes;
//	
//	public long getLikesCountByID(Long id) {
//		return jpaQueryFactory.selectFrom(qLikes).where(qLikes.user.id.eq(id)).fetchCount();
//	}
	
	
}
