package com.ggomzirak.service;

import java.util.ArrayList;
import java.util.List;

import com.ggomzirak.db.entity.Shorts;
import com.ggomzirak.db.entity.User;
import com.ggomzirak.db.entity.Likes;
import com.ggomzirak.dto.MainSearchGetReq;
import com.ggomzirak.dto.MainSearchGetRes;
import com.ggomzirak.dto.ShortsListGetRes;
import com.ggomzirak.dto.ShortsRegisterPostReq;
import com.ggomzirak.dto.ShortsSearchGetReq;

public interface ShortsService {

	Shorts createShorts(ShortsRegisterPostReq registerInfo);
	Shorts getShortsById(Long id);
	List<ShortsListGetRes> getShorts(int page);
	void modifyShortsLikes(Likes likes);
	boolean isNotDupLikes(Likes likes);
	List<ShortsListGetRes> getSearchResult(ShortsSearchGetReq req);
	Long getShortsCommentsCount(Long id);
	void addViewCount(Long id);
	List<ShortsListGetRes> getUserShorts(Long userId);
	List<ShortsListGetRes> getUserLiked(Long userId);
}
