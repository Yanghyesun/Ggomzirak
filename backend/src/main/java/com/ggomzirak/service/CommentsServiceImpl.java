package com.ggomzirak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggomzirak.db.entity.Comments;
import com.ggomzirak.db.entity.PostType;
import com.ggomzirak.db.entity.User;
import com.ggomzirak.db.repository.CommentsRepository;
import com.ggomzirak.db.repository.CommentsRepositorySupport;
import com.ggomzirak.db.repository.UserRepository;
import com.ggomzirak.dto.CommentsListGetRes;
import com.ggomzirak.dto.CommentsRegisterPostReq;

@Service("commentsService")
public class CommentsServiceImpl implements CommentsService {
	@Autowired
	CommentsRepository commentsRepository;

	@Autowired
	CommentsRepositorySupport commentsRepositorySupport;
	
	@Autowired
	UserRepository userRepository;
	// 댓글 등록------------------------------------------------
	@Override
	public Comments createComments(CommentsRegisterPostReq registerInfo) {
		// TODO Auto-generated method stub
		Comments comments = new Comments();
		User user = userRepository.getById(registerInfo.getUserId());
		PostType postType = new PostType();
		postType.setId(registerInfo.getTypeId());
		if(postType.getId()==3)
			user.setPoints(user.getPoints()+100);//질문글에 댓글 달리면 100포인트
	
		comments.setPostId(registerInfo.getPostId());
		comments.setPostType(postType);
		comments.setUser(user);
		comments.setContents(registerInfo.getContents());
		return commentsRepository.save(comments);
	}

	// 댓글 목록------------------------------------------------------
	@Override
	public List<CommentsListGetRes> getComments(Long postId, int page, Long type) {
		// TODO Auto-generated method stub
		return commentsRepositorySupport.getComments(postId, page, type);
	}
	
	// 댓글 목록------------------------------------------------------
	@Override
	public Long getCommentsCount(Long id,Long typeId) {
		// TODO Auto-generated method stub
		return commentsRepositorySupport.getCommentsCount(id,typeId);
	}
}
