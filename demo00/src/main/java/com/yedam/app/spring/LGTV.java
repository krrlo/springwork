package com.yedam.app.spring;

import org.springframework.stereotype.Component;

@Component("lgtv")
public class LGTV implements TV {

	@Override
	public void on() {
		System.out.println("lgtv켯다아아아아");

	}

}
