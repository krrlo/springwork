package com.yedam.app.emp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpVO;

@Controller
public class EmpTestController {

	@GetMapping("getTestEmp")
	@ResponseBody // JSP가 아닌 데이터를 반환
	public EmpVO getEmpInfo(EmpVO empVO) {
		empVO.setEmployeeId(1000);
		return empVO;
	}

	@PostMapping("getTestEmp")
	@ResponseBody // JSP가 아닌 데이터를 반환
	public EmpVO getEmpInfoPost(EmpVO empVO) {
		empVO.setEmployeeId(9000);
		return empVO;
	}

}
