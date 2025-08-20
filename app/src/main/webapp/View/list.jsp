<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Task" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>やること一覧</title>
  <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
<header>やること一覧</header>

<main class="mainlist">
  <table class="task-table">
    <thead>
      <tr>
        <th>やること</th>
        <th>日程</th>
        <th>詳細</th>
        <th>削除</th>
      </tr>
    </thead>
    <tbody>
      <%
        List<Model.Task> taskList = (List<Model.Task>) request.getAttribute("taskList");
        if (taskList != null && !taskList.isEmpty()) {
          for (Model.Task task : taskList) {
      %>
        <tr>
          <td><%= task.getTitle() %></td>
          <td><%= task.getScheduleDate() %></td>
          <td>
            <form action="<%= request.getContextPath() %>/Detail" method="get">
              <input type="hidden" name="id" value="<%= task.getId() %>">
              <button type="submit" class="button btn-blue">詳細</button>
            </form>
          </td>
          <td>
            <form action="<%= request.getContextPath() %>/DeleteTask" method="post">
              <input type="hidden" name="id" value="<%= task.getId() %>">
              <button type="submit" class="button btn-red">削除</button>
            </form>
          </td>
        </tr>
      <%
          }
        } else {
      %>
        <tr><td colspan="4">タスクがありません</td></tr>
      <%
        }
      %>
    </tbody>
  </table>

  <div class="button-area">
    <a href="<%= request.getContextPath() %>/Index" class="button btn-blue back-button">戻る</a>
  </div>
</main>

<footer><small>© Sample</small></footer>
</body>
</html>
