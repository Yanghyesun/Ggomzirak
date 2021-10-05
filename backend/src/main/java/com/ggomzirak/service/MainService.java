package com.ggomzirak.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ggomzirak.db.entity.Live;
import com.ggomzirak.db.entity.Shorts;
import com.ggomzirak.db.entity.User;
import com.ggomzirak.dto.MainSearchGetReq;
import com.ggomzirak.dto.MainSearchGetRes;
import com.ggomzirak.dto.MainUsersGetRes;
import com.ggomzirak.dto.TipsListGetRes;

@Service
public interface MainService {
	
	List<Shorts> getTopShorts();
	List<User> getTopUsers();
	List<TipsListGetRes> getTopTips();
	void setRanking(List<User> list);
	List<MainUsersGetRes> getCalcTopUsers();
	List<Live> getTopLive();

}
