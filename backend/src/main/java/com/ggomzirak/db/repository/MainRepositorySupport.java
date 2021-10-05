package com.ggomzirak.db.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ggomzirak.db.entity.QComments;
import com.ggomzirak.db.entity.QRanking;
import com.ggomzirak.db.entity.QShorts;
import com.ggomzirak.db.entity.QTips;
import com.ggomzirak.db.entity.QUser;
import com.ggomzirak.db.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class MainRepositorySupport {
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QShorts qShorts = QShorts.shorts;
	QTips qTips = QTips.tips;
	QComments qComments = QComments.comments;
	QUser qUser = QUser.user;
	QRanking qRanking = QRanking.ranking1;

	public List<User> getCalcTopUsers() {
		List<User> list = jpaQueryFactory.selectFrom(qUser).join(qRanking).where(qRanking.user.id.eq(qUser.id)).fetch();
		return list;
	}
}
