package com.ggomzirak.db.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ggomzirak.db.entity.Likes;
import com.ggomzirak.db.entity.Ranking;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {
    // 아래와 같이, Query Method 인터페이스(반환값, 메소드명, 인자) 정의를 하면 자동으로 Query Method 구현됨.
    Optional<Ranking> findById(Long id);
	Ranking findByRanking(int rank);
}
