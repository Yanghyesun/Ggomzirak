package com.ggomzirak.service;

import java.util.List;

import com.ggomzirak.db.entity.Likes;
import com.ggomzirak.db.entity.Tips;
import com.ggomzirak.dto.TipsListGetRes;
import com.ggomzirak.dto.TipsRegisterPostReq;

/**
 *	꿀팁글 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface TipsService {

	Tips createTips(TipsRegisterPostReq registerInfo);
	Tips getTipsById(Long id);
	List<TipsListGetRes> getTips(int page);
	void modifyTipsLikes(Likes likes);
	List<TipsListGetRes> getSearchTips(String key,String word, int page);
	boolean isNotDupLikes(Likes likes);
	List<TipsListGetRes> getUserTips(Long userId);
	List<TipsListGetRes> getUserLiked(Long userId);
}
