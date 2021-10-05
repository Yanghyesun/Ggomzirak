package com.ggomzirak.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggomzirak.db.entity.Likes;
import com.ggomzirak.db.entity.PostType;
import com.ggomzirak.db.entity.Shorts;
import com.ggomzirak.db.entity.User;
import com.ggomzirak.db.repository.ShortsRepository;
import com.ggomzirak.db.repository.ShortsRepositorySupport;
import com.ggomzirak.db.repository.TipsRepository;
import com.ggomzirak.db.repository.TipsRepositorySupport;
import com.ggomzirak.db.repository.UserRepository;
import com.ggomzirak.db.repository.LikesRepository;
import com.ggomzirak.db.entity.Shorts;
import com.ggomzirak.dto.ShortsListGetRes;
import com.ggomzirak.dto.ShortsRegisterPostReq;
import com.ggomzirak.dto.ShortsSearchGetReq;
import com.querydsl.core.types.dsl.Expressions;

@Service
public class ShortsServiceImpl implements ShortsService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	ShortsRepository shortsRepository;
	
	@Autowired
	LikesRepository likesRepository;
	
	@Autowired
	ShortsRepositorySupport shortsRepositorySupport;
	
	@Override
	public Shorts createShorts(ShortsRegisterPostReq registerInfo) {
		Shorts shorts = new Shorts();
		shorts.setTitle(registerInfo.getTitle());
		shorts.setContents(registerInfo.getContents());
		shorts.setThumbnail(registerInfo.getThumbnail());
		shorts.setTags(registerInfo.getTags());
//		shorts.setEdit_time(Timestamp.valueOf(LocalDateTime.now()));
//		shorts.setLikes(0);
//		shorts.setTime(Timestamp.valueOf(LocalDateTime.now()));
		shorts.setVideo(registerInfo.getVideo());
//		shorts.setViews(0L);
		User user = userRepository.findById(registerInfo.getUserId()).get();
		user.setPoints(user.getPoints()+100);
		shorts.setUser(new User(registerInfo.getUserId()));

		userRepository.save(user);
		return shortsRepository.save(shorts);
	}

	@Override
	public Shorts getShortsById(Long id) {
		return shortsRepository.findById(id).get();
	}

	@Override
	public List<ShortsListGetRes> getShorts(int page) {
		return shortsRepositorySupport.getShortsByPage(page);
	}

	@Override
	public void modifyShortsLikes(Likes likes) {
		Shorts shorts = shortsRepository.findById(likes.getPostId()).get();
		shorts.setLikes(shorts.getLikes()+1);
		
		shortsRepository.save(shorts);
		likesRepository.save(likes);
		
	}

	@Override
	public boolean isNotDupLikes(Likes likes) {
		Long count = shortsRepositorySupport.isNotDupLikes(likes);
		if(count == 0) {
			return true;
		}else {
			return false;			
		}
	}

	@Override
	public List<ShortsListGetRes> getSearchResult(ShortsSearchGetReq req) {
		return shortsRepositorySupport.getSearchResult(req);
	}

	@Override
	public Long getShortsCommentsCount(Long id) {
		return shortsRepositorySupport.getShortsCommentsCount(id);
	}

	@Override
	public void addViewCount(Long id) {
		Shorts shorts = shortsRepository.findById(id).get();
		shorts.setViews(shorts.getViews()+1);
		
		shortsRepository.save(shorts);
	}

	@Override
	public List<ShortsListGetRes> getUserShorts(Long userId) {
		return shortsRepositorySupport.getShortsByUser(userId);
	}

	@Override
	public List<ShortsListGetRes> getUserLiked(Long userId) {
		return shortsRepositorySupport.getUserLiked(userId);
	}

	
}
