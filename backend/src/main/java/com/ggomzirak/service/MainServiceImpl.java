package com.ggomzirak.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggomzirak.db.entity.Live;
import com.ggomzirak.db.entity.QRanking;
import com.ggomzirak.db.entity.QUser;
import com.ggomzirak.db.entity.Ranking;
import com.ggomzirak.db.entity.Shorts;
import com.ggomzirak.db.entity.User;
import com.ggomzirak.db.repository.LikesRepository;
import com.ggomzirak.db.repository.LikesRepositorySupport;
import com.ggomzirak.db.repository.LiveRepository;
import com.ggomzirak.db.repository.MainRepository;
import com.ggomzirak.db.repository.MainRepositorySupport;
import com.ggomzirak.db.repository.RankingRepository;
import com.ggomzirak.db.repository.ShortsRepository;
import com.ggomzirak.db.repository.ShortsRepositorySupport;
import com.ggomzirak.db.repository.TipsRepository;
import com.ggomzirak.db.repository.TipsRepositorySupport;
import com.ggomzirak.db.repository.UserRepository;
import com.ggomzirak.dto.MainSearchGetReq;
import com.ggomzirak.dto.MainSearchGetRes;
import com.ggomzirak.dto.MainUsersGetRes;
import com.ggomzirak.dto.TipsListGetRes;

@Service
public class MainServiceImpl implements MainService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ShortsRepository shortsRepository;
	@Autowired
	ShortsRepositorySupport shortsRepositorySupport;

	@Autowired
	MainRepository mainRepository;
	@Autowired
	MainRepositorySupport mainRepositorySupport;

	@Autowired
	TipsRepository tipsRepository;
	@Autowired
	TipsRepositorySupport tipsRepositorySupport;
	
	@Autowired
	RankingRepository rankingRepository;
	@Autowired
	LikesRepository likesRepository;
	@Autowired
	LikesRepositorySupport likesRepositorySupport;
	
	@Autowired
	LiveRepository liveRepository;
	

	QUser qUsers = QUser.user;
	QRanking qRanking = QRanking.ranking1;

	@Override
	public List<Shorts> getTopShorts() {

		return mainRepository.findTop4ByOrderByViewsDesc();
	}

	@Override
	public List<User> getTopUsers() {
		List<User> user = userRepository.findTopWriteUser();
//		List<User> u = new ArrayList<User>();
//		Ranking rank = new Ranking();
//		for(int i = 0;i <= 2; i++) {
//			rank.setRanking(i+1);
//			User temp = userRepository.getById(user.get(i).getUser_id());
//			rank.setUser(temp);
//			rank.setLikes(user.get(i).getLikes());
//			rank.setPosts(user.get(i).getTotal() - user.get(i).getLikes());
//		}
//		for(int i = 0;i <= 2; i++) {
//			rank.setRanking(i+1);
//			User temp = userRepository.getById(user.get(i).get(qRanking.user).getId());
//			rank.setUser(temp);
//			rank.setLikes(user.get(i).get(qRanking.likes));
//			rank.setPosts(user.get(i).get(qRanking.Posts) - user.get(i).get(qRanking.likes));
//		}
//		QRanking qRanking = QRanking.ranking1;
//		for (Tuple tuple : user) {
//			System.out.println(tuple.get(qUsers.id));
//		}

		return user;
	}

	@Override
	public List<TipsListGetRes> getTopTips() {
		return tipsRepositorySupport.findTop4ByOrderByLikesDesc();
	}

	@Override
	public void setRanking(List<User> list) {
		int rank = 1;
		for(User u : list) {
			//랭킹 설정
			Ranking ranking = rankingRepository.findByRanking(rank);
			int posts = userRepository.getPostsById(u.getId());
			int likes = (int)likesRepository.getLikesCountByID(u.getId());
			int views = (int)shortsRepositorySupport.getViewsCountByID(u.getId()).intValue();
			ranking.setUser(u);
			ranking.setPosts(posts);
			ranking.setLikes(likes);
			ranking.setViews(views);
			ranking.setRanking(rank++);
			rankingRepository.save(ranking);
			
			//1000포인트 지급
			User user = u;
			user.setPoints(user.getPoints()+ 1000);
			userRepository.save(user);
			
			System.out.println(u.getEmail());
		}
		System.out.println();
	}

	@Override
	public List<MainUsersGetRes> getCalcTopUsers() {
		List<Ranking> rankingList = rankingRepository.findAll();
		List<MainUsersGetRes> list = new ArrayList<MainUsersGetRes>();
		for(Ranking ranking : rankingList) {
//			String name = userRepository.getById(ranking.getUser().getId()).getName();
			MainUsersGetRes user = new MainUsersGetRes(userRepository.getById(ranking.getUser().getId()), ranking.getViews(), ranking.getLikes(), ranking.getPosts(), ranking.getRanking());
			list.add(user);
		}
		return list;
	}

	@Override
	public List<Live> getTopLive() {
		List<Live> topLives = liveRepository.getTopLive();
		int rest = 4 - topLives.size();
		topLives.addAll(liveRepository.getTopRecord(rest));
		return topLives;
	}

}
