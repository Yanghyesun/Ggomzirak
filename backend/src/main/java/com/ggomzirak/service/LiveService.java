package com.ggomzirak.service;

import java.util.List;

import com.ggomzirak.db.entity.Live;
import com.ggomzirak.dto.LiveInfoPostReq;
import com.ggomzirak.dto.LiveRecordReq;
import com.ggomzirak.dto.LiveSessionReq;

public interface LiveService {

	Live createLive(LiveInfoPostReq liveInfo);

	Live getliveInfo(Long liveId);

	Live exitLive(Long liveId);

	Live getViewerNum(Long liveId);

	int checkLive(String userId);

	List<Live> getLiveAll();

	Live saveRecord(LiveRecordReq recordInfo);

	List<Live> getRecordByUserId(String userId);

	List<Live> getRecordAll();

	Live removeRecordByLiveId(Long liveId);

	Live calcViewers(LiveSessionReq sessionInfo);

	Live getRecordByLiveId(Long liveid);

	
}
