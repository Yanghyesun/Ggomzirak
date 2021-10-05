package com.ggomzirak.db.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ggomzirak.db.entity.QComments;
import com.ggomzirak.db.entity.QQna;
import com.ggomzirak.db.entity.QTips;
import com.ggomzirak.dto.CommentsListGetRes;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

/**
 * 댓글 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class CommentsRepositorySupport {
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QTips qTips = QTips.tips;
	QQna qQna = QQna.qna;
	QComments qComments = QComments.comments;

	public List<CommentsListGetRes> getComments(Long postId, int page, Long type) {
		// TODO Auto-generated method stub
		int offset = 10 * (page - 1);
		List<CommentsListGetRes> list = jpaQueryFactory
				.select(Projections.constructor(CommentsListGetRes.class, qComments)).from(qComments)
				.where(qComments.postType.id.eq(type).and(qComments.postId.eq(postId)))
				.orderBy(qComments.time.asc()).offset(offset).limit(10).fetch();
		return list;
	}

	public Long getCommentsCount(Long id,Long typeId) {
		// TODO Auto-generated method stub
		Long count = jpaQueryFactory.selectFrom(qComments)
				.where(qComments.postType.id.eq(typeId).and(qComments.postId.eq(id)))
				.fetchCount();
		return count;
	}

}
