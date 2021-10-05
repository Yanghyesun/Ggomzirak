package com.ggomzirak.db.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ggomzirak.db.entity.Likes;
import com.ggomzirak.db.entity.QComments;
import com.ggomzirak.db.entity.QLikes;
import com.ggomzirak.db.entity.QTips;
import com.ggomzirak.dto.ShortsListGetRes;
import com.ggomzirak.dto.TipsListGetRes;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

/**
 * 꿀팁글 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class TipsRepositorySupport {
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QTips qTips = QTips.tips;
	QComments qComments = QComments.comments;
	QLikes qLikes  = QLikes.likes;

	public List<TipsListGetRes> getTipsByPage(int page) {
		// TODO Auto-generated method stub
		int offset = 16 * (page - 1);

		/*
		 * select count(c.id), t.* from tips as t left outer join comments as c on
		 * (c.post_id = t.id and c.type_id=1) group by t.id order by time desc;
		 */

		List<TipsListGetRes> list = jpaQueryFactory
				.select(Projections.constructor(TipsListGetRes.class, qTips, qComments.id.count())).from(qTips)
				.leftJoin(qComments).on(qTips.id.eq(qComments.postId).and((qComments.postType.id).eq((long) 1)))
				.groupBy(qTips.id).orderBy(qTips.time.desc()).offset(offset).limit(16).fetch();

		return list;
	}

	public List<TipsListGetRes> getSearchTips(String key, String word, int page) {
		// TODO Auto-generated method stub
		int offset = 16 * (page - 1);
		List<TipsListGetRes> list = new ArrayList<>();

		if (key.equals("title"))
			list = jpaQueryFactory.select(Projections.constructor(TipsListGetRes.class, qTips, qComments.id.count()))
					.from(qTips).leftJoin(qComments)
					.on(qTips.id.eq(qComments.postId).and((qComments.postType.id).eq((long) 1)))
					.where(qTips.title.contains(word)).groupBy(qTips.id).orderBy(qTips.time.desc()).offset(offset)
					.limit(16).fetch();
		else if (key.equals("contents"))
			list = jpaQueryFactory.select(Projections.constructor(TipsListGetRes.class, qTips, qComments.id.count()))
					.from(qTips).leftJoin(qComments)
					.on(qTips.id.eq(qComments.postId).and((qComments.postType.id).eq((long) 1)))
					.where(qTips.contents.contains(word)).groupBy(qTips.id).orderBy(qTips.time.desc())
					.offset(offset).limit(16).fetch();
		else if (key.equals("tags"))
			list = jpaQueryFactory.select(Projections.constructor(TipsListGetRes.class, qTips, qComments.id.count()))
					.from(qTips).leftJoin(qComments)
					.on(qTips.id.eq(qComments.postId).and((qComments.postType.id).eq((long) 1)))
					.where(Expressions.asString(";").concat(qTips.tags).like("%;" + word + ";%"))
					.groupBy(qTips.id)
					.orderBy(qTips.time.desc()).offset(offset).limit(16).fetch();
		else
			list = jpaQueryFactory.select(Projections.constructor(TipsListGetRes.class, qTips, qComments.id.count()))
					.from(qTips).leftJoin(qComments)
					.on(qTips.id.eq(qComments.postId).and((qComments.postType.id).eq((long) 1)))
					.where(qTips.user.name.contains(word)).orderBy(qTips.time.desc()).offset(offset).limit(16)
					.fetch();

		return list;
	}

	public Long isNotDupLikes(Likes likes) {
		// TODO Auto-generated method stub
		Long count = jpaQueryFactory.selectFrom(qLikes)
				.where(qLikes.postType.id.eq((long)1).and(qLikes.postId.eq(likes.getPostId())).and(qLikes.user.id.eq(likes.getUser().getId())))
				.fetchCount();
		return count;
	}

	public List<TipsListGetRes> findTop4ByOrderByLikesDesc() {
		// TODO Auto-generated method stub
		List<TipsListGetRes> list = jpaQueryFactory
				.select(Projections.constructor(TipsListGetRes.class, qTips, qComments.id.count())).from(qTips)
				.leftJoin(qComments).on(qTips.id.eq(qComments.postId).and((qComments.postType.id).eq((long) 1)))
				.groupBy(qTips.id).orderBy(qTips.likes.desc()).limit(4).fetch();

		return list;
	}

	public Long getTipsCommentsCount(Long id) {
		// TODO Auto-generated method stub
		Long count = jpaQueryFactory.selectFrom(qComments)
				.where(qComments.postType.id.eq((long) 1).and(qComments.postId.eq(id)))
				.fetchCount();
		return count;
	}

	public List<TipsListGetRes> getTipsByUser(Long userId) {
		List<TipsListGetRes> list = jpaQueryFactory
				.select(Projections.constructor(TipsListGetRes.class, qTips, qComments.id.count())).from(qTips)
				.leftJoin(qComments).on(qTips.id.eq(qComments.postId).and((qComments.postType.id).eq((long) 1)))
				.where(qTips.user.id.eq(userId))
				.groupBy(qTips.id).fetch();

		return list;
	}

	public List<TipsListGetRes> getUserLiked(Long userId) {
		List<TipsListGetRes> list = jpaQueryFactory
				.select(Projections.constructor(TipsListGetRes.class, qTips, qComments.id.count())).from(qTips)
				.leftJoin(qLikes).on(qLikes.postId.eq(qTips.id))
				.leftJoin(qComments).on(qTips.id.eq(qComments.postId).and((qComments.postType.id).eq((long) 1)))
				.where(qLikes.user.id.eq(userId).and(qLikes.postType.id.eq((long) 1)))
				.groupBy(qTips.id).fetch();

		return list;
	}

}
