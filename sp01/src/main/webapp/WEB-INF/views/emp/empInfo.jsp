<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- http://localhost:8080/app/empInfo?employeeId=100 -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>사원 정보 조회 및 수정</title>
	</head>
	<body>
		<form>
			<div>
				<label>
					employee_id :
					<input type="number" name="employeeId" value="${empInfo.employeeId}" />
				</label>
				<br />
				<label>
					last_name:
					<input type="text" name="lastName" value="${empInfo.lastName}" />
				</label>
				<br />
				<label>
					email :
					<input type="email" name="email" value="${empInfo.email}" />
				</label>
				<br />
				<label>
					hire_date : <input type="date" name="hireDate" value="<fmt:formatDate
						value="${empInfo.hireDate}"
						pattern="yyyy-MM-dd"
					/>" />
				</label>
				<br />
				<label>
					job_id :
					<input type="text" name="jobId" value="${empInfo.jobId}" />
				</label>
				<br />
				<label>
					salary :
					<input type="number" name="salary" value="${empInfo.salary}" />
				</label>
			</div>
			<div>
				<button type="button" onclick="location.href='empList'">목록으로</button>
				<!-- 페이지 전환 없이(submit 버튼 x) ajax 호출 -->
				<button type="button" id="updateBtn">수정</button>
				<button onclick="location.href='empDelete?eid=${empInfo.employeeId}'" type="button">삭제</button>
				<!--  public String empDelete(@RequestParam Integer eid) 를 키 값으로 -->
			</div>
		</form>
		<script>
			document.querySelector('#updateBtn').addEventListener('click', updateEmpInfo);

			function updateEmpInfo(event) {
				// form 내부의 입력 태그를 기반으로 정보를 가져옴
				let empInfo = getEmpInfo();
				// 해당 정보를 기반으로 ajax 호출, queryString 형태(URLSearchParams)
				fetch('empUpdate', { method: 'post', body: new URLSearchParams(empInfo) })  //보내는 데이터. empinfo >>객체(수정할 정보가 여러건인경우)  쿼리스트링은 객체가 아님...
					.then((response) => response.json())    //객체를 통신용으로 변환하는 작업이 필요함 >>> URLSearchParams
					.then((result) => {
						console.log('쿼리스트링', result);
					})
					.catch((err) => console.log(err));
					
					
					//제이슨
					//header제이슨 타입으로 보낸다는 것을 알려줘야함 
				fetch('empUpdateAjax', { method: 'post', headers : {'content-type' : 'application/json'}, body: JSON.stringify(empInfo) })  
				.then((response) => response.json())   
				.then((result) => {
					console.log('json', result);
				})
				.catch((err) => console.log(err));
				
					
			}//함수
			
			

			function getEmpInfo() {
				// form 하위 (공백) 인풋 tag 전부 검색
				let inputList = document.querySelectorAll('form input');
				let objData = {};
				// tag의 name으로 키:밸류 형태의 objData 생성, name="employeeId" value="${empInfo.employeeId}" -> employeeId: ${empInfo.employeeId}
				inputList.forEach((tag) => { //
					objData[tag.name] = tag.value;    //tag.name에 각 value의 이름  <input type="text" name="jobId"
				});

				return objData;
			}
		</script>
	</body>
</html>