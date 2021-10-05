package com.ggomzirak.db.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ggomzirak.db.entity.Live;

@Repository
public interface LiveRepository extends JpaRepository<Live, Long>{
	
	@Query(nativeQuery = true, 
			value = "select count(*) from live where user_id = ?1 and endtime is null")
	int checkLive(Long userId);

	@Query(nativeQuery = true,
			value = "select *\r\n" + 
					"from live\r\n" + 
					"where endtime is null\r\n" + 
					"order by viewers desc\r\n" + 
					"limit 4;")
	List<Live> getTopLive();

	@Query(nativeQuery = true,
			value = "select *\r\n" + 
					"from live\r\n" + 
					"where endtime is null\r\n" + 
					"order by viewers desc;")
	List<Live> getLiveAll();

	@Query(nativeQuery = true,
			value = "select *\r\n" + 
					"from live\r\n" + 
					"where video is not null and user_id = ?;")
	List<Live> getRecordByUserId(Long id);

	@Query(nativeQuery = true,
			value = "select *\r\n" + 
					"from live\r\n" + 
					"where video is not null\r\n" + 
					"order by maxviewers desc;")
	List<Live> getRecordAll();

	@Query(nativeQuery = true,
			value = "select *\r\n" + 
					"from live\r\n" + 
					"where id = ?;")
	Live getLiveInfoByLiveId(Long liveId);
	
	@Query(nativeQuery = true,
			value = "select *\r\n" + 
					"from live\r\n" + 
					"where endtime is not null and video is not null\r\n" + 
					"order by maxviewers desc\r\n" + 
					"limit ?;")
	List<Live> getTopRecord(int rest);

}
