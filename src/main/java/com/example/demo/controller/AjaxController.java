package com.example.demo.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.communications.RaspPiClient;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
*
* [설명] Ajax transmission Controller setting
*
* @file : AjaxController.java
* @package : com.example.demo.controller
* @project : LockerApplication
* @author : Seung-hun Ji
* @since : 2018. 5. 19.
*/
@RestController // @Controller와 각 RequestMapping에 @ResponseBody 내용을 추가해준 기능과 동일
public class AjaxController {
	
	//CORS(Cross-origin resource sharing)을 통해 다른 도메인의 서버 url 호출 시 나타나는 보안문제를 해결 할 수 있다.
	@CrossOrigin("*")
	@RequestMapping(value="/object", method = RequestMethod.POST)
	public @ResponseBody Map<Integer, Object> sendOb(@RequestBody DoorRequest params, Principal principal) {
		
		// @RequestBody로 받은 데이터 확인 -> json 데이터로 받은 값을 객체에 자동으로 넣어서 사용한다.
		System.out.println(params.toString()); 
		
		// RaspPi와 통신하기 위해 클라이언트 객체 생성
		RaspPiClient cm = new RaspPiClient();
		String sendData = params.getKey() + "," + params.getBox() + "," + principal.getName();
		
		cm.clientRun(sendData);
		
		// ajax에게 돌려줄 데이터 (key, value) 생성
		HashMap<Integer, Object> map = new HashMap<Integer, Object>();
		
		// 임의의 데이터 생성 후 map에 등록
		for(int i=0; i < 10; i++) {
		
			DoorRequest data = new DoorRequest();
			
			data.setKey("test" + i);
			Integer num = i;
			data.setBox(num + i);
			
			map.put(i, data);
		}
		// jackson 라이브러리를 통해 Map 자료형을 자동으로 json으로 변환해서 ajax에게 돌려준다.
		return map;
	}
	
}

@Getter @Setter @ToString
class DoorRequest {
	private String key;
	private int box;
}
