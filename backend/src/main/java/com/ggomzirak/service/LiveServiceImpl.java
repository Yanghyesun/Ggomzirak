package com.ggomzirak.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggomzirak.db.entity.Live;
import com.ggomzirak.db.entity.User;
import com.ggomzirak.db.repository.LiveRepository;
import com.ggomzirak.db.repository.UserRepository;
import com.ggomzirak.dto.LiveInfoPostReq;
import com.ggomzirak.dto.LiveRecordReq;
import com.ggomzirak.dto.LiveSessionReq;

@Service
public class LiveServiceImpl implements LiveService{

	@Autowired
	LiveRepository liveRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Live createLive(LiveInfoPostReq liveInfo) {
		Live live = new Live();
		live.setTitle(liveInfo.getTitle());
		live.setContents(liveInfo.getContents());
		live.setUser(userRepository.findByUserId(liveInfo.getUserId()));
		live.setType(liveInfo.getType());
		live.setViewers(1);
		live.setMaxviewers(1);
		live.setThumbnail(liveInfo.getThumbnail());

		return liveRepository.save(live); 
	}

	@Override
	public Live getliveInfo(Long liveId) {
		return liveRepository.getLiveInfoByLiveId(liveId);
	}

	@Override
	public Live exitLive(Long liveId) {
		Live live = liveRepository.getLiveInfoByLiveId(liveId);
		LocalDateTime date = LocalDateTime.now();
		live.setEndtime(date);
		liveRepository.save(live);
		
		return live;
	}

	@Override
	public Live getViewerNum(Long liveId) {
		return liveRepository.getLiveInfoByLiveId(liveId);
	}

	@Override
	public int checkLive(String userId) {
		return liveRepository.checkLive(userRepository.findByUserId(userId).getId());
	}

	@Override
	public List<Live> getLiveAll() {
		return liveRepository.getLiveAll();
	}

	@Override
	public Live saveRecord(LiveRecordReq recordInfo) {
		Live live = liveRepository.getLiveInfoByLiveId(recordInfo.getLiveid());
		live.setVideo(recordInfo.getRecord());
		liveRepository.save(live);
		return live;
	}

	@Override
	public List<Live> getRecordByUserId(String userId) {
		Long id = userRepository.findByUserId(userId).getId();
		return liveRepository.getRecordByUserId(id);
	}

	@Override
	public List<Live> getRecordAll() {
		return liveRepository.getRecordAll();
	}

	@Override
	public Live removeRecordByLiveId(Long liveId) {
		Live live = liveRepository.getLiveInfoByLiveId(liveId);
		live.setVideo(null);
		liveRepository.save(live);
		return live;
	}

	@Override
	public Live calcViewers(LiveSessionReq sessionInfo) {
		Live live = liveRepository.getLiveInfoByLiveId(sessionInfo.getLiveid());
		live.setViewers(sessionInfo.getViewers());
		
		if(sessionInfo.getViewers() > live.getMaxviewers()) {
			live.setMaxviewers(sessionInfo.getViewers());
		}
		
		liveRepository.save(live);
		return live;
	}

	@Override
	public Live getRecordByLiveId(Long liveid) {
		return liveRepository.getLiveInfoByLiveId(liveid);
	}


}
