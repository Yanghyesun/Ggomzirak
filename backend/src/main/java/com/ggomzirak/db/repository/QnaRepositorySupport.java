package com.ggomzirak.db.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ggomzirak.db.entity.QComments;
import com.ggomzirak.db.entity.QQna;
import com.ggomzirak.dto.QnaListGetRes;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

/**
 * 질문게시판 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class QnaRepositorySupport {
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QQna qQna = QQna.qna;
	QComments qComments = QComments.comments;

	public List<QnaListGetRes> getQnaByPage(int page) {
		// TODO Auto-generated method stub
		int offset = 16 * (page - 1);

		List<QnaListGetRes> list = jpaQueryFactory
				.select(Projections.constructor(QnaListGetRes.class, qQna, qComments.id.count())).from(qQna)
				.leftJoin(qComments).on(qQna.id.eq(qComments.postId).and((qComments.postType.id).eq((long) 3)))
				.groupBy(qQna.id).orderBy(qQna.time.desc()).offset(offset).limit(16).fetch();

		return list;
	}

	public List<QnaListGetRes> getSearchQna(String key, String word, int page) {
		// TODO Auto-generated method stub
		int offset = 16 * (page - 1);
		List<QnaListGetRes> list = new ArrayList<>();

		if (key.equals("title"))
			list = jpaQueryFactory.select(Projections.constructor(QnaListGetRes.class, qQna, qComments.id.count()))
					.from(qQna).leftJoin(qComments)
					.on(qQna.id.eq(qComments.postId).and((qComments.postType.id).eq((long) 3)))
					.where(qQna.title.contains(word)).groupBy(qQna.id).orderBy(qQna.time.desc()).offset(offset)
					.limit(16).fetch();
		else if (key.equals("contents"))
			list = jpaQueryFactory.select(Projections.constructor(QnaListGetRes.class, qQna, qComments.id.count()))
					.from(qQna).leftJoin(qComments)
					.on(qQna.id.eq(qComments.postId).and((qComments.postType.id).eq((long) 3)))
					.where(qQna.contents.contains(word)).groupBy(qQna.id).orderBy(qQna.time.desc())
					.offset(offset).limit(16).fetch();
		else if (key.equals("tags"))
			list = jpaQueryFactory.select(Projections.constructor(QnaListGetRes.class, qQna, qComments.id.count()))
					.from(qQna).leftJoin(qComments)
					.on(qQna.id.eq(qComments.postId).and((qComments.postType.id).eq((long) 3)))
					.where(Expressions.asString(";").concat(qQna.tags).like("%;" + word + ";%")).groupBy(qQna.id)
					.orderBy(qQna.time.desc()).offset(offset).limit(16).fetch();
		else
			list = jpaQueryFactory.select(Projections.constructor(QnaListGetRes.class, qQna, qComments.id.count()))
					.from(qQna).leftJoin(qComments)
					.on(qQna.id.eq(qComments.postId).and((qComments.postType.id).eq((long) 3)))
					.where(qQna.user.name.contains(word)).orderBy(qQna.time.desc()).offset(offset).limit(16)
					.fetch();

		return list;	}

	public List<QnaListGetRes> getQnaByUser(Long userId) {
		List<QnaListGetRes> list = jpaQueryFactory
				.select(Projections.constructor(QnaListGetRes.class, qQna, qComments.id.count())).from(qQna)
				.leftJoin(qComments).on(qQna.id.eq(qComments.postId).and((qComments.postType.id).eq((long) 3)))
				.where(qQna.user.id.eq(userId))
				.groupBy(qQna.id).fetch();

		return list;
	}

}