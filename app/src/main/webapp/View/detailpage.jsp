<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.Task" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Task task = (Task) request.getAttribute("task");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>詳細</title>
  <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>

<header>詳細</header>

<main class="mainschedule">
  <div class="form">
    <div class="form-item">
      <label>やること名</label>
      <p class="detail-box"><c:out value="${task.title}" /></p>
    </div>

    <div class="form-item">
      <label>詳細</label>
      <p class="detail-box"><c:out value="${task.detail}" /></p>
    </div>

    <div class="form-item">
      <label>予定日</label>
      <p class="detail-box"><c:out value="${task.scheduleDate}" /></p>
    </div>

    <div style="text-align: center; margin-top: 20px;">
      <a href="<c:url value='/List'/>" class="button btn-blue">一覧に戻る</a>
    </div>
  </div>
</main>

<footer><small>© Sample</small></footer>

</body>
</html>


