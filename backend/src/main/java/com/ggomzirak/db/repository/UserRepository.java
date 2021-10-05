package com.ggomzirak.db.repository;

import java.util.List;
import java.util.Optional;

import org.kurento.client.internal.server.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ggomzirak.db.entity.User;
import com.ggomzirak.dto.TopUsers;
import com.querydsl.core.Tuple;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email); // 이미 email을 통해 생성된 사용자인지 체크
    @Query(nativeQuery = true, value = "select * from user u\r\n" + 
    		"join(\r\n" + 
    		"select sum(t1_c) total, t1_c likes, user_id from (\r\n" + 
    		"select *\r\n" + 
    		"from (\r\n" + 
    		"		select sum(u.likes)*10 t1_c, u.user_id\r\n" + 
    		"			from (select likes, user_id from tips\r\n" + 
    		"				union all\r\n" + 
    		"				select likes, user_id from shorts\r\n" + 
    		"			) u\r\n" + 
    		"			where u.user_id is not null\r\n" + 
    		"			group by u.user_id\r\n" + 
    		") t1\r\n" + 
    		"union all(\r\n" + 
    		"	select count(*) t2_c, u.user_id\r\n" + 
    		"	from (select user_id from tips\r\n" + 
    		"	union all\r\n" + 
    		"	select user_id from shorts\r\n" + 
    		"	union all\r\n" + 
    		"	select user_id from qna\r\n" + 
    		"	) u\r\n" + 
    		"	where u.user_id is not null\r\n" + 
    		"	group by u.user_id\r\n" + 
    		")\r\n" + 
    		"union all(\r\n" + 
    		"	select sum(views), user_id from shorts\r\n" + 
    		"	where user_id is not null\r\n" + 
    		"	group by user_id\r\n" + 
    		")) t2\r\n" + 
    		"group by user_id\r\n" + 
    		")result\r\n" + 
    		"where result.user_id = u.id\r\n" + 
    		"order by total desc limit 0,3;")
	List<User> findTopWriteUser();
    
    @Query(nativeQuery = true, value = "select count(*) c\r\n" + 
    		"from (select user_id from tips\r\n" + 
    		"	union all\r\n" + 
    		"	select user_id from shorts\r\n" + 
    		"	union all\r\n" + 
    		"	select user_id from qna\r\n" + 
    		") u\r\n" + 
    		"where u.user_id= ?1\r\n" + 
    		"group by u.user_id\r\n" + 
    		"order by c desc limit 0,3;")
	int getPostsById(Long param);
    
	User findByRefreshtoken(String refreshtoken);
	User findByUserId(String userId);
	Optional<User> findById(Long id);
	boolean existsByUserId(String userId);
}
