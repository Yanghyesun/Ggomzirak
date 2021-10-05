package com.ggomzirak.db.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ggomzirak.db.entity.Live;
import com.ggomzirak.db.entity.QLive;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class LiveRepositorySupport {

	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QLive qLive = QLive.live;
	
//	public Live getLiveInfoByEndtime(Long userId) {
//		System.out.println("들어옴");
//		List<Live> live = jpaQueryFactory
//				.selectFrom(qLive)
//				.where(qLive.user.id.eq(userId).and(qLive.endtime.isNull()))
//				.fetch();
//		System.out.println(live.get(0).toString());
//		return live.get(0);
//	}
	
}
