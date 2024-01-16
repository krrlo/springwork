package com.yedam.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MapperTest {

	@Autowired
	EmpMapper empMapper;

	// 전체조회
	@Test
	public void selectAll() {
		List<EmpVO> list = empMapper.selectEmpList();
		assertTrue(!list.isEmpty());
	}

	// 단건조회
	// @Test
	public void selectInfo() {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(100);
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		assertEquals(findVO.getLastName(), "King");
		// 두 값 비교해서 같으면 true
	}

	// 등록
	// @Test
	public void insertInfo() {
		EmpVO empVO = new EmpVO();
		empVO.setLastName("Hong");
		empVO.setEmail("kdddng@google.com");
		empVO.setHireDate(new Date("24/01/15"));
		empVO.setJobId("IT_PROG");
		empVO.setSalary(10000);

		int result = empMapper.insertEmpInfo(empVO);
		assertNotEquals(result, 0);
		// result 대신에 select key를 동작시키는 순간 employeeid로 넘어가서
		// empVO.getEmployeeId(), 0 )
	}

	// 수정
	// @Test
	public void updateInfo() {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(1001);
		// 원데이터를 먼저가져와서
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		// 가져온 데이터에 이름을 변경, 업데이트진행
		findVO.setLastName("gggg");
		int result = empMapper.updateEmpInfo(findVO);
		assertNotEquals(result, 0);
	}

	//
	// @Test
	public void updateInfoDynamic() {
		// 객체생성
		EmpVO empVO = new EmpVO();
		// 값 집어넣기
		empVO.setEmployeeId(1000);
		empVO.setSalary(5200);
		int result = empMapper.updateEmpInfoDynamic(empVO);
		// last_name = any가 들어가게됨
		assertNotEquals(result, 0);
	}

	// 삭제
	// @Test
	public void deleteInfo() {
		int result = empMapper.deleteEmpInfo(207);
		assertNotEquals(result, 0);
	}

}
