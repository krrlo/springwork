<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container">
	<h1>도서등록</h1>

	<form name="insertForm" action="bookInsert" method="post">
		<table class="table">
			<tr>
				<th>도서번호</th>
				<td><input type="number" name="bookNo" readonly /></td>
			</tr>
			<tr>
				<th>도서명</th>
				<td><input type="text" name="bookName" /></td>
			</tr>

			<tr>
				<th>도서표지</th>
				<td><input type="text" name="bookCoverimg" /></td>
			</tr>
			<tr>
				<th>출판일자</th>
				<td><input type="date" name="bookDate" /></td>
			</tr>

			<tr>
				<th>금액</th>
				<td><input type="text" name="bookPrice" /></td>
			</tr>

			<tr>
				<th>출판사</th>
				<td><input type="text" name="bookPublisher" /></td>
			</tr>

			<tr>
				<th>도서소개</th>
				<td><input type="text" name="bookInfo" /></td>
			</tr>


		</table>
		<button type="submit">등록</button>
	</form>
</div>

<script>
	
	$('form[name="insertForm"]').on("submit", bookInsertJq);

	function bookInsertJq(e) {
		e.preventDefault();

		let bname = $('[name="bookName"]');
	

		if (bname.val() == "") {
			alert("도서명이 입력되지 않았습니다");
			bname.focus();
			return;
		}
		
		
		insertForm.submit();
		alert("도서등록이 완료되었습니다");
		
	}
</script>
