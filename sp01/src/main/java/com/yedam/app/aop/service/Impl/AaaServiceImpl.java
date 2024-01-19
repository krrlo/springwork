package com.yedam.app.aop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yedam.app.aop.mapper.AaaMapper;
import com.yedam.app.aop.service.AaaService;

@Service
public class AaaServiceImpl implements AaaService {

	@Autowired
	AaaMapper aaaMapper;

	@Transactional // service밑에서만 동작하는 어노테이션
	public void insert() {
		aaaMapper.insertAaa("101");
		aaaMapper.insertAaa("a101"); // 얘가오류나면 위에꺼도 못들어가게 전체 롤백처리

	}

}
