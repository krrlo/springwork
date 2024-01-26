package com.example.demo.member;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.user.mapper.MemberMapper;
import com.example.demo.user.service.MemberVO;

@SpringBootTest
public class MemberMapperTest {

	@Autowired
	MemberMapper mapper;

	@Test
	public void 회원조회() {
		String id = "user2";
		MemberVO vo = mapper.getMember(id);
		System.out.println(vo);
		assertThat(vo.getMid()).isEqualTo(id);
	}

}
