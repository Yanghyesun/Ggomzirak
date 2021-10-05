package com.ggomzirak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggomzirak.db.entity.Qna;
import com.ggomzirak.db.entity.User;
import com.ggomzirak.db.repository.QnaRepository;
import com.ggomzirak.db.repository.QnaRepositorySupport;
import com.ggomzirak.db.repository.UserRepository;
import com.ggomzirak.dto.QnaRegisterPostReq;
import com.ggomzirak.dto.QnaListGetRes;

@Service("qnaSrvice")
public class QnaServiceImpl implements QnaService {
	@Autowired
	QnaRepository qnaRepository;
	
	@Autowired
	QnaRepositorySupport qnaRepositorySupport;
	
	@Autowired
	UserRepository userRepository;
	
	
	//글 등록------------------------------------------------
	@Override
	public Qna createQna(QnaRegisterPostReq registerInfo) {
		// TODO Auto-generated method stub
		Qna qna = new Qna();
		User user = userRepository.findById(registerInfo.getUserId()).get();
		user.setPoints(user.getPoints()+50);

		qna.setUser(user);
		qna.setTitle(registerInfo.getTitle());
		qna.setContents(registerInfo.getContents());
		qna.setImage(registerInfo.getImage());
		qna.setTags(registerInfo.getTags());

		userRepository.save(user);
		return qnaRepository.save(qna);
	}
	
	//id번 글 상세------------------------------------------------
	@Override
	public Qna getQnaById(Long id) {
		// TODO Auto-generated method stub
		return qnaRepository.findById(id).get();
	}
	
	//글 목록------------------------------------------------------
	@Override
	public List<QnaListGetRes> getQna(int page) {
		// TODO Auto-generated method stub
		return qnaRepositorySupport.getQnaByPage(page);
	}
	

	// 글 검색-------------------------------------------------------
	@Override
	public List<QnaListGetRes> getSearchQna(String key, String word, int page) {
		// TODO Auto-generated method stub
		return qnaRepositorySupport.getSearchQna(key, word, page);
	}

	@Override
	public List<QnaListGetRes> getUserQna(Long userId) {
		return qnaRepositorySupport.getQnaByUser(userId);
	}
}
