package com.ggomzirak.db.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ggomzirak.db.entity.Comments;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
	   
	}
