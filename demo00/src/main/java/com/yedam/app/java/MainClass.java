package com.yedam.app.java;

import com.yedam.app.spring.TV;

public class MainClass {

	public static void main(String[] args) {
//		TV tv = new LGTV();
//		tv.on(); // 자바방식

		TV tv = new SamsungTV();
		tv.on(); // 자바방식

	}

}
