<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="container">
  <h1>도서별 대여매출 현황</h1>
  <table class="table">
    <thead>
      <tr style="text-align: center">
        <th>도서번호</th>
        <th>도서명</th>
        <th>대여총계</th>
        <th>대여횟수</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${rentList}" var="book" varStatus="sts">
        <tr>
          <td style="text-align: center">${book.bookNo}</td>
          <td>${book.bookName}</td>
          <td style="text-align: right">${book.price}</td>
          <td style="text-align: right">${book.cnt}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>
