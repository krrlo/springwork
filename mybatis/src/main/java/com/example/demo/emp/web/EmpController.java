package com.example.demo.emp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.emp.mapper.EmpMapper;

@Controller
public class EmpController {

	@Autowired
	EmpMapper mapper;

//관리자만 접근가능하게 /admin어드민추가
	@GetMapping("/admin/empList")
	public String list(Model model) {
		model.addAttribute("list", mapper.getEmpList(null));
		return "emp/empList";

	}

}
