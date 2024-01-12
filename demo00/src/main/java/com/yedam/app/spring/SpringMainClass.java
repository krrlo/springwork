package com.yedam.app.spring;

import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {

		GenericApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		System.out.println("스프링방식..");
		TV tv = (TV) ctx.getBean("tv"); // new 사용하지 않고..
		// TV tv = (TV) ctx.getBean("TV.class"); // 2. 빈등록방식 (인터페이스 자체를 넘기는것)
		tv.on();
		// TV lgTv = (TV) ctx.getBean("lgtv");
		// lgTv.on();

	}

}
