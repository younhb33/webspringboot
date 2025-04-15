<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    Date today = new Date();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bootstrap 회원가입 + Spring-boot</title>
<link rel="stylesheet" href="./css/bootstrap.css?v=<%=today%>">
<script src="./js/bootstrap.js?v=<%=today%>"></script>
</head>
<body>
<form id="frm" method="post" action="./join.do">
<input type="hidden" name="MCODE" value="1">
<input type="hidden" name="MJOIN" value="WEB">


<div class="input-group mb-3 width1">
  <span class="input-group-text" id="basic-addon1">아이디</span>
  <input type="text" name="MID" class="form-control" placeholder="아이디를 입력하세요">
</div>

<div class="input-group mb-3 width1">
  <span class="input-group-text" id="basic-addon1">패스워드</span>
  <input type="password" name="MPASS" class="form-control" placeholder="패스워드를 입력하세요">
</div>

<div class="input-group mb-3 width1">
  <span class="input-group-text" id="basic-addon1">고객명</span>
  <input type="text" name="MNAME" class="form-control" placeholder="고객명을 입력하세요">
</div>

<div class="input-group mb-3 width1">
  <span class="input-group-text" id="basic-addon1">닉네임</span>
  <input type="text" name="MNICK" class="form-control" placeholder="닉네임을 입력하세요">
</div>

<div class="input-group mb-3 width1">
  <span class="input-group-text" id="basic-addon1">이메일</span>
  <input type="text" name="MEMAIL" class="form-control" placeholder="이메일을 입력하세요">
</div>

<div class="input-group mb-3 width1">
  <span class="input-group-text" id="basic-addon1">연락처</span>
  <input type="text" name="MHP" class="form-control" placeholder="'-'을 빼고 숫자만 입력하세요">
</div>

<div class="mb-3">
<button type="button" id="btn" class="btn btn-submit">회원가입</button>

</form>
<script type="module">
import {memberjoin} from "./test.js?v=<%=today%>";
</script>
</body>
</html>