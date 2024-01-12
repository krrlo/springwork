package com.yedam.app.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("tv") // 빈등록방식 1 : 이름을 붙혀줌 TV tv = (TV) ctx.getBean("tv");
public class SamsungTV implements TV {
	// 3.
	@Autowired
	Speaker speaker; // 클래스를 필드로 사용한것

	// 1. 생성자방식
	@Autowired // 의존성.. 사용하고자 하는 생성자 위에 넣어주기 autowired의 위치를 변경
	SamsungTV(Speaker speaker) {
		this.speaker = speaker;

	}

	// 기본 생성자 추가 setter 이용하려고
	SamsungTV() {
	}

	// 2. setter방식
	@Autowired
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	@Override
	public void on() {
		System.out.println("삼성티비");
		speaker.on(); // 불러옴..
	}

}
