package com.ggomzirak.service;

import java.util.List;

import com.ggomzirak.db.entity.Comments;
import com.ggomzirak.dto.CommentsListGetRes;
import com.ggomzirak.dto.CommentsRegisterPostReq;

/**
 *	댓글 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface CommentsService {

	Comments createComments(CommentsRegisterPostReq registerInfo);
	List<CommentsListGetRes> getComments(Long postId, int page, Long type);
	Long getCommentsCount(Long id,Long typeId);
}
