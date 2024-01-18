<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<div class="container">
  <h1>게시글등록</h1>

  <form name="insertForm" action="boardInsert" method="post">
    <table class="table">
      <tr>
        <th>제목</th>
        <td><input type="text" name="title" /></td>
      </tr>
      <tr>
        <th>작성자</th>
        <td><input type="text" name="writer" /></td>
      </tr>
      <tr>
        <th>내용</th>
        <td><textarea name="contents"></textarea></td>
      </tr>

      <tr>
        <th>이미지</th>
        <td><input type="file" name="image" /></td>
      </tr>
    </table>
    <button type="submit">등록</button>
  </form>
</div>

<script>
  //form태그 insertForm 이름을 부여하면 이름을 기반으로 바로 불러올수있음.
  //자바스크립트 널 체크
  document
    .querySelector('form[name="insertForm"]')
    .addEventListener("submit", boardInsertJs); //submit도 이벤트임.

  document
    .querySelector('form[name="insertForm"]')
    .removeEventListener("submit", boardInsertJs); //submit도 이벤트임.

  function boardInsertJs(event) {
    event.preventDefault(); //submit을 일단 멈춤

    let title = document.getElementsByName("title")[0]; //배열로 넘어온대
    let writer = document.getElementsByName("writer")[0];
    console.log(title);

    if (title.value == "") {
      //value는 null을 반환하지 않음. 비교 값은 '' 공백이여야함
      alert("제목입력x");
      title.focus();
      return; //함수 자체(submit)를 종료 시킴
    }

    if (writer.value == "") {
      alert("작성자입력x");
      writer.focus();
      return;
    }

    insertForm.submit(); //if문이 동작되지 않았다면 필수값은 다 입력되었다는말 >> insert실행  insertForm >> 하나의 변수로 인식됨.
  }

  //제이쿼리 널 체크

  $('form[name="insertForm"]').on("submit", boardInsertJq);

  function boardInsertJq(e) {
    e.preventDefault();

    let title = $('[name="title"]');
    let writer = $('[name="writer"]');

    if (title.val() == "") {
      alert("제목입력해");
      title.focus();
      return;
    }

    if (writer.val() == "") {
      alert("작성자입력해");
      writer.focus();
      return;
    }

    insertForm.submit();
  }
</script>
