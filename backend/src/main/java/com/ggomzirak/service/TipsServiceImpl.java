package com.ggomzirak.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggomzirak.db.entity.Likes;
import com.ggomzirak.db.entity.Tips;
import com.ggomzirak.db.entity.User;
import com.ggomzirak.db.repository.CommentsRepositorySupport;
import com.ggomzirak.db.repository.LikesRepository;
import com.ggomzirak.db.repository.TipsRepository;
import com.ggomzirak.db.repository.TipsRepositorySupport;
import com.ggomzirak.db.repository.UserRepository;
import com.ggomzirak.dto.TipsListGetRes;
import com.ggomzirak.dto.TipsRegisterPostReq;

@Service("tipsService")
public class TipsServiceImpl implements TipsService {
	@Autowired
	TipsRepository tipsRepository;

	@Autowired
	TipsRepositorySupport tipsRepositorySupport;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	LikesRepository likesRepository;
	
	@Autowired
	CommentsRepositorySupport commentsRepositorySupport;

	// 글 등록------------------------------------------------
	@Override
	public Tips createTips(TipsRegisterPostReq registerInfo) {
		// TODO Auto-generated method stub
		Tips tips = new Tips();
		User user = userRepository.findById(registerInfo.getUserId()).get();
		user.setPoints(user.getPoints()+100);
		
		tips.setUser(user);
		tips.setTitle(registerInfo.getTitle());
		tips.setContents(registerInfo.getContents());
		tips.setImage(registerInfo.getImage());
		tips.setThumbnail(registerInfo.getThumbnail());
		tips.setTags(registerInfo.getTags());
		
		userRepository.save(user);
		return tipsRepository.save(tips);
	}

	// id번 글 상세------------------------------------------------
	@Override
	public Tips getTipsById(Long id) {
		// TODO Auto-generated method stub
		return tipsRepository.findById(id).get();
	}

	// 글 목록------------------------------------------------------
	@Override
	public List<TipsListGetRes> getTips(int page) {
		// TODO Auto-generated method stub
		return tipsRepositorySupport.getTipsByPage(page);
	}

	// 좋아요+1-----------------------------------------------------
	@Override
	public void modifyTipsLikes(Likes likes) {
		// TODO Auto-generated method stub
		System.out.println(likes.getPostId());
		Tips tips = tipsRepository.findById(likes.getPostId()).get();
		tips.setLikes(tips.getLikes() + 1);
		tipsRepository.save(tips);
		likesRepository.save(likes);
	}

	// 글 검색-------------------------------------------------------
	@Override
	public List<TipsListGetRes> getSearchTips(String key, String word, int page) {
		// TODO Auto-generated method stub
		return tipsRepositorySupport.getSearchTips(key, word, page);
	}

	//좋아요 중복 확인----------------------------------------------------
	@Override
	public boolean isNotDupLikes(Likes likes) {
		// TODO Auto-generated method stub
		Long count = tipsRepositorySupport.isNotDupLikes(likes);
		if(count==0)
			return true;//좋아요 누른적이 없다는 뜻
		else
			return false;
	}

	@Override
	public List<TipsListGetRes> getUserTips(Long userId) {
		return tipsRepositorySupport.getTipsByUser(userId);
	}

	@Override
	public List<TipsListGetRes> getUserLiked(Long userId) {
		return tipsRepositorySupport.getUserLiked(userId);
	}
}
