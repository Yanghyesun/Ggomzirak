package com.ggomzirak.db.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ggomzirak.db.entity.Likes;
import com.ggomzirak.db.entity.QComments;
import com.ggomzirak.db.entity.QLikes;
import com.ggomzirak.db.entity.QShorts;
import com.ggomzirak.db.entity.Shorts;
import com.ggomzirak.dto.ShortsListGetRes;
import com.ggomzirak.dto.ShortsSearchGetReq;
import com.ggomzirak.dto.ShortsListGetRes;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class ShortsRepositorySupport {
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QShorts qShorts = QShorts.shorts;
	QComments qComments = QComments.comments;
	QLikes qLikes  = QLikes.likes;

	public List<ShortsListGetRes> getShortsByPage(int page) {

		int offset = 16 * (page - 1);
		
		List<ShortsListGetRes> list = jpaQueryFactory
				.select(Projections.constructor(ShortsListGetRes.class, qShorts, qComments.id.count())).from(qShorts)
				.leftJoin(qComments).on(qShorts.id.eq(qComments.postId).and((qComments.postType.id).eq((long) 2)))
				.groupBy(qShorts.id).orderBy(qShorts.time.desc()).offset(offset).limit(16).fetch();

		return list;

	}

	public List<ShortsListGetRes> getSearchResult(ShortsSearchGetReq req) {
		int offset = 16 * (req.getPage() - 1);
		List<ShortsListGetRes> list = new ArrayList<>();

		if (req.getKey().equals("title"))
			list = jpaQueryFactory.select(Projections.constructor(ShortsListGetRes.class, qShorts, qComments.id.count()))
					.from(qShorts).leftJoin(qComments)
					.on(qShorts.id.eq(qComments.postId).and((qComments.postType.id).eq((long) 2)))
					.where(qShorts.title.contains(req.getWord())).groupBy(qShorts.id).orderBy(qShorts.time.desc()).offset(offset)
					.limit(16).fetch();
		else if (req.getKey().equals("contents"))
			list = jpaQueryFactory.select(Projections.constructor(ShortsListGetRes.class, qShorts, qComments.id.count()))
					.from(qShorts).leftJoin(qComments)
					.on(qShorts.id.eq(qComments.postId).and((qComments.postType.id).eq((long) 2)))
					.where(qShorts.contents.contains(req.getWord())).groupBy(qShorts.id).orderBy(qShorts.time.desc())
					.offset(offset).limit(16).fetch();
		else if (req.getKey().equals("tags"))
			list = jpaQueryFactory.select(Projections.constructor(ShortsListGetRes.class, qShorts, qComments.id.count()))
					.from(qShorts).leftJoin(qComments)
					.on(qShorts.id.eq(qComments.postId).and((qComments.postType.id).eq((long) 2)))
					.where(Expressions.asString(";").concat(qShorts.tags).like("%;" + req.getWord() + ";%")).groupBy(qShorts.id)
					.orderBy(qShorts.time.desc()).offset(offset).limit(16).fetch();
		else
			list = jpaQueryFactory.select(Projections.constructor(ShortsListGetRes.class, qShorts, qComments.id.count()))
					.from(qShorts).leftJoin(qComments)
					.on(qShorts.id.eq(qComments.postId).and((qComments.postType.id).eq((long) 2)))
					.where(qShorts.user.name.contains(req.getWord())).orderBy(qShorts.time.desc()).offset(offset).limit(16)
					.fetch();

		return list;
		
	}

	public Long getShortsCommentsCount(Long id) {
		Long count = jpaQueryFactory.selectFrom(qComments)
				.where(qComments.postType.id.eq((long) 2).and(qComments.postId.eq(id)))
				.fetchCount();
		return count;
	}

	public Long isNotDupLikes(Likes likes) {
		Long count = jpaQueryFactory.selectFrom(qLikes)
		.where(qLikes.postType.id.eq((long) 2).and(qLikes.postId.eq(likes.getPostId())).and(qLikes.user.id.eq(likes.getUser().getId())))
		.fetchCount();
		System.out.println(count);
		return count;
	}

	public Long getViewsCountByID(Long id) {
		return jpaQueryFactory.select(qShorts.views.sum()).from(qShorts).where(qShorts.user.id.eq(id)).fetchOne();
	}

	public List<ShortsListGetRes> getShortsByUser(Long userId) {
		List<ShortsListGetRes> list = jpaQueryFactory
				.select(Projections.constructor(ShortsListGetRes.class, qShorts, qComments.id.count())).from(qShorts)
				.leftJoin(qComments).on(qShorts.id.eq(qComments.postId).and((qComments.postType.id).eq((long) 2)))
				.where(qShorts.user.id.eq(userId))
				.groupBy(qShorts.id).fetch();

		return list;
	}

	public List<ShortsListGetRes> getUserLiked(Long userId) {
		List<ShortsListGetRes> list = jpaQueryFactory
				.select(Projections.constructor(ShortsListGetRes.class, qShorts, qComments.id.count())).from(qShorts)
				.leftJoin(qLikes).on(qLikes.postId.eq(qShorts.id))
				.leftJoin(qComments).on(qShorts.id.eq(qComments.postId).and((qComments.postType.id).eq((long) 2)))
				.where(qLikes.user.id.eq(userId).and(qLikes.postType.id.eq((long) 2)))
				.groupBy(qShorts.id).fetch();

		return list;
	}
}
