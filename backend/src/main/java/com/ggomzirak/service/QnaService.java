package com.ggomzirak.service;

import java.util.List;

import com.ggomzirak.db.entity.Qna;
import com.ggomzirak.dto.QnaRegisterPostReq;
import com.ggomzirak.dto.QnaListGetRes;

/**
 *	질문글 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface QnaService {

	Qna createQna(QnaRegisterPostReq registerInfo);
	Qna getQnaById(Long id);
	List<QnaListGetRes> getQna(int page);
	List<QnaListGetRes> getSearchQna(String key, String word, int page);
	List<QnaListGetRes> getUserQna(Long userId);
}
