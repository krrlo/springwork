package com.yedam.app.emp.service;

import lombok.Data;

@Data
public class EmpVO {

	// 컬럼명 그대로 써야함
	String employee_id;
	String last_name;
	String email;
	String hire_date;
	String job_id;

}
