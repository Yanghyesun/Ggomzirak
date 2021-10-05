package com.ggomzirak.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ggomzirak.dto.TagRegisterPostReq;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;

@Api(value = "태그 생성 API", tags = { "Tags" })
@RestController
@RequestMapping("/api/v1/tag")
public class TagController {
	@PostMapping()
	@ApiOperation(value = "태그 생성", notes = "제목과 내용으로 태그를 생성한다")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			 @ApiResponse(code = 500, message = "서버 오류") })
	public List<String> createTag(@RequestBody TagRegisterPostReq registerInfo) throws IOException {
		List<String> tagList = new ArrayList<>();
		HashMap<String, Integer> hashmap = new HashMap<String, Integer>();

		Komoran komoran = new Komoran(DEFAULT_MODEL.LIGHT);
		//제목의 키워드를 찾는다-----------------------------------------------------------------
		KomoranResult titleResult = komoran.analyze(registerInfo.getTitle());
				
//		List<String> titleTag = new ArrayList<>();
//		titleTag = komoran.analyze(registerInfo.getTitle()).getNouns();
//		Set<String> titleTag = new HashSet<>(komoran.analyze(registerInfo.getTitle()).getNouns());
		
		for(String str : titleResult.getNouns())
		{
			if(!hashmap.containsKey(str))
				hashmap.put(str, 1);
			else
				hashmap.put(str,hashmap.get(str)+1);
		}
		
//		System.out.println("제목 키워드"+titleTag.toString());
		
		//본문의 키워드를 찾는다-----------------------------------------------------------------
		KomoranResult contentsResult = komoran.analyze(registerInfo.getContents());
//		System.out.println("본문 키워드"+contentsResult.getNouns().toString());

		for(String str : contentsResult.getNouns())
		{
			if(!hashmap.containsKey(str))
				hashmap.put(str, 1);
			else
				hashmap.put(str,hashmap.get(str)+1);
		}
		
		System.out.println(hashmap.toString());

        List<String> keySet = new ArrayList<>(hashmap.keySet());

        // 개수 기준으로 내림차순 정렬.
        keySet.sort((o1, o2) -> hashmap.get(o2) - hashmap.get(o1));
        
//        System.out.println("==내림차순 정렬==");
//        for (String key : keySet) {
//        	if(hashmap.get(key)>1)
//            System.out.println(key+" "+hashmap.get(key)+"개");
//        }

		//제목 키워드 + 본문 키워드 -----------------------------------------------------------
		int count = 0;

		for (String key : keySet) {

			count++;
			tagList.add(key);
			if (count >= 10)
				break;
		} // 개수 한개 이상인것만 최대 10개
        
		return tagList;
	}

}
