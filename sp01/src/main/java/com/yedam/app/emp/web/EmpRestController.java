package com.yedam.app.emp.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

//@Controller
@CrossOrigin("*") // cors 설정 모든 origin에 대해 cors 허용
@RestController // 각 메소드가 가지고있는 @ResponseBody 생략가능
public class EmpRestController {

	// REST방식 >>페이지를 반환하지 않기 때문에 @ResponseBody 써야한대
	// 모델을 사용하지 않음.서비스의 결과를 다이렉트로 넘겨버릴수 있기 때문ㅇ ㅔ ??????????
	@Autowired
	EmpService empService;

	// 전체조회
	@GetMapping("emps")
	// @ResponseBody
	public List<EmpVO> getEmpList() {
		return empService.getEmpAll();
	}

	// 단건조회 //쿼리스트링 잘 사용하지 않음 경로에 붙어서 @GetMapping("emps/{id}") >>노드에서 :/id
	// 로했던것처럼.. @PathVariable >>경로에서 데이터를 가져오려고 씀 // rest방식에서 많이 사용됨 .
	@GetMapping("emps/{id}")
	// @ResponseBody
	public EmpVO getEmpInfo(@PathVariable Integer id) {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(id);
		return empService.getEmpInfo(empVO);
	}

	// 등록 제이슨 형태로 데이터가 넘어올것임 >>@RequestBody
	@PostMapping("emps")
	// @ResponseBody
	public Map<String, Object> insertEmpInfo(@RequestBody EmpVO empVO) {
		int eid = empService.insertEmpInfo(empVO);
		Map<String, Object> map = new HashMap<>();
		map.put("result", eid);
		return map;
	}

	// 수정(단건과 등록 결합 상태) //@PathVariable >>경로에 들어온 값 사용
	@PutMapping("emps/{id}")
	// @ResponseBody
	public Map<String, Object> UpdateEmpInfo(@PathVariable Integer id, @RequestBody EmpVO empVO) {
		empVO.setEmployeeId(id);
		return empService.updateEmpInfo(empVO);
	}

	// 삭제
	@DeleteMapping("emps/{id}")
	// @ResponseBody
	public boolean deleteEmpInfo(@PathVariable Integer id) {
		return empService.deleteEmpInfo(id);
	}
}
