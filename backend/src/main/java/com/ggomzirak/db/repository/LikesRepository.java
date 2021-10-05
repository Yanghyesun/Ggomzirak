package com.ggomzirak.db.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ggomzirak.db.entity.Likes;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
	
	 @Query(nativeQuery = true, value = "select sum(u.likes) c\r\n" + 
	 		"from (select likes, user_id from tips\r\n" + 
	 		"	union all\r\n" + 
	 		"	select likes, user_id from shorts\r\n" + 
	 		") u\r\n" + 
	 		"where u.user_id = ?1\r\n" + 
	 		"group by u.user_id\r\n" + 
	 		"order by c desc limit 0,3;")
	public int getLikesCountByID(Long id);
}
