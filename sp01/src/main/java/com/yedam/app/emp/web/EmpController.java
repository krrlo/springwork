package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Controller
public class EmpController {
	@Autowired
	EmpService empService;

	// 여러 개의 서비스 등록 가능
//	@Autowired
//	DeptService detpService;

//	GET: 단순 조회, 빈 페이지 호출
//	POST: 데이터 조작 (등록, 수정, 삭제)

//	전체 조회
	@GetMapping("empList")
	// Model: req, resp 동시에 처리할 수 있도록 스프링에서 제공하는 객체
	public String getEmpList(Model model) {
		List<EmpVO> list = empService.getEmpAll();
		model.addAttribute("empList", list); // jsp파일로 보냄
		// servlet-context.xml에서 prefix, suffix 만나서 /WEB-INF/views/emp/empList.jsp 로 변경
		return "emp/empList"; // 프론트로 리턴 , jsp파일로 보냄
	}

//	사원 조회
	@GetMapping("empInfo")
	public String getEmpInfo(EmpVO empVO, Model model) {
		EmpVO findVO = empService.getEmpInfo(empVO);
		model.addAttribute("empInfo", findVO);
		return "emp/empInfo";
	}

//	동일 경로에 두가지의 기능 (GET, POST)
//	사원 등록 - FORM  //등록화면만 띄워줌 
	@GetMapping("empInsert")
	public String insertEmpInfoForm() {
		return "emp/empInsert";
	}

//	사원 등록 - PROCESS
	@PostMapping("empInsert")
	// 새로운 객체
	public String insertEmpInfoProcess(EmpVO empVO) {
		int empId = empService.insertEmpInfo(empVO);

		String path = null;

		// redirect 요청 시 개발자모드 Network > Header 상태코드 302 발생 -> 이동 재요청 -> 상태코드 200 (2번
		// 이동)
		if (empId > -1) {
			// 정상 등록 시 단건 조회로 redirect
			path = "redirect:empInfo?employeeId=" + empId;
		} else {
			// 등록 실패 시 전체 목록으로 redirect
			path = "redirect:empList";
		}
		return path;
	}

//	사원 수정 - PROCESS: ****Ajax => @ResponseBody 사용(쿼리스트링, JSON 둘 다 가능)
	// 아작스 기반으로 요청하고 성공/실패 가림
//	1) 쿼리스트링형태로 데이터가 넘어온다면.. (매개변수: 커맨드 객체 > empvo)
	@PostMapping("empUpdate")
	@ResponseBody // ajax용
	public Map<String, Object> empUpdateProcess(EmpVO empVO) {
		return empService.updateEmpInfo(empVO);
	}

//	2) JSON 타입으로 데이터가 넘어온다면.. (매개변수: @RequestBody)
	@PostMapping("empUpdateAjax")
	@ResponseBody
	public Map<String, Object> empUpdateAjaxProcess(@RequestBody EmpVO empVO) {
		return empService.updateEmpInfo(empVO);
	}

//	사원 삭제 - PROCESS
// 기본적으로 post 

	@GetMapping("empDelete")
	public String empDelete(@RequestParam Integer eid) {
		empService.deleteEmpInfo(eid);
		return "redirect:empList"; // 전체조회로 돌아가기
	}

}