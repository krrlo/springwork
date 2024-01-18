<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<div class="container">
  <h1>게시글 조회</h1>

  <table class="table">
    <tr>
      <th>번호</th>
      <td>${boardInfo.bno}</td>
    </tr>

    <tr>
      <th>제목</th>
      <td>${boardInfo.title}</td>
    </tr>

    <tr>
      <th>작성자</th>
      <td>${boardInfo.writer}</td>
    </tr>

    <tr>
      <th>내용</th>
      <td>
        <textarea rows="2" cols="2" style="width: 100px" readonly>
                ${boardInfo.contents}</textarea
        >
      </td>
    </tr>

    <tr>
      <th>이미지</th>
      <!-- <td><img src="/app/resources/${boardInfo.image}" /></td> -->
      <td><img src="<c:url value="/resources/${boardInfo.image}" />"></td>
    </tr>

    <tr>
      <th>작성날짜</th>
      <td>${boardInfo.regdate}</td>
    </tr>
  </table>

  <button type="button">수정</button>
  <button type="button">삭제</button>
</div>
