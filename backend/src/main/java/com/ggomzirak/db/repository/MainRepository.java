package com.ggomzirak.db.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ggomzirak.db.entity.Shorts;

@Repository
public interface MainRepository extends JpaRepository<Shorts, Long> {
	List<Shorts> findTop4ByOrderByLikesDesc();
	List<Shorts> findTop4ByOrderByViewsDesc();
}
