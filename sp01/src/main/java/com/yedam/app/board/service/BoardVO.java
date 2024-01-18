package com.yedam.app.board.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BoardVO {

	private Integer bno;
	private String title;
	private String contents;
	private String writer;
	// input type = "date" 하던지 text로해서 사용자로 하여금 -- 로 입력받게
	@DateTimeFormat(pattern = "yyyy-MM-dd") // 데이터를 자동 포맷할때 어떤 패턴으로 되어있는지 지정, 날짜는 yyyy-MM-dd로 넘어와야함
	private Date regdate;
	private Date updatedate; // yyyy/mm//dd 화면에서 받아올 정보가 아니라 포맷 사용안함..
	private String image;

}
