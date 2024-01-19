<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<div class="container">
  <h1>게시글 수정</h1>

  <form name="updateForm">
    <table class="table">
      <tr>
        <th>번호</th>
        <td>
          <input type="text" name="bno" value="${boardInfo.bno }" readonly />
        </td>
      </tr>
      <tr>
        <th>제목</th>
        <td><input type="text" name="title" value="${boardInfo.title }" /></td>
      </tr>
      <tr>
        <th>작성자</th>
        <td>
          <input type="text" name="writer" value="${boardInfo.writer }" />
        </td>
      </tr>
      <tr>
        <th>내용</th>
        <td><textarea name="contents">${boardInfo.contents }</textarea></td>
      </tr>

      <tr>
        <th>이미지</th>
        <td><input type="text" name="image" value="${boardInfo.image }" /></td>
      </tr>
    </table>
    <button type="button">수정</button>
  </form>
</div>

<script>
  //수정을 누르면 ajax가 돌게
  $("form > button:contains(수정)").on("click", boardUpdateAjax); //얘는 submit 이벤트 안됨    <button type="button">수정</button>  type이 submit이 아니니까..

  function boardUpdateAjax(e) {
    //통신 진행 여부 결정  //제목, 작성자 , 사용자가 두 항목 모두 입력해야함
    if (!validation()) return;

    //통신하기 위한 데이터 가져오기
    let boardDta = getBoardInfo();
    console.log("data", boardDta);

    $.ajax("boardUpdate", {
      type: "post",
      //data: boardDta, //객체형태로 데이터를 넘겨야함 => 제이쿼리가 queryString 형태로 자동으로 변환해줌
      //제이슨타입으로 보낼꺼면.. @Requestparam
      data: JSON.stringify(boardDta),
      contentType: "application/json",
    })
      .done((result) => {
        console.log("Re", result);
      })
      .fail((err) => console.log(err));
  }

  function validation() {
    let title = $('[name="title"]');
    let writer = $('[name="writer"]');

    if (title.val() == "") {
      alert("제목입력해");
      title.focus();
      return false;
    }

    if (writer.val() == "") {
      alert("작성자입력해");
      writer.focus();
      return false;
    }

    return true;
  } //validation 의 결과가 필요함

  //.serializeArray(); 폼태그 name value 끄집어내는 메소드. 입력태그면 다 불러오는게 가능. 텍스트어레아도..ㅋ
  function getBoardInfo() {
    let formAry = $('form[name="updateForm"]').serializeArray(); // [{…}, {…}, {…}, {…}, {…}]
    console.log(formAry);

    let formObj = {};
    $(formAry).each((idx, tag) => {
      console.log("Ttttt", tag);
      formObj[tag.name] = tag.value;
    });

    console.log(formObj);
    return formObj;
  }
</script>
