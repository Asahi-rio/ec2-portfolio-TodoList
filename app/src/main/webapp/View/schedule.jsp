<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    java.util.Calendar cal = java.util.Calendar.getInstance();
    int currentYear = cal.get(java.util.Calendar.YEAR);
    int currentMonth = cal.get(java.util.Calendar.MONTH) + 1; // 0-indexed → +1
    int currentDay = cal.get(java.util.Calendar.DAY_OF_MONTH);
%>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8" />
  <title>やること登録</title>
  <link rel="stylesheet" href="<c:url value='/css/style.css'/>" />
</head>
<body>

  <header>やること登録</header>

  <main class="mainschedule">
    <form class="form" action="<c:url value='/Schedule'/>" method="post" accept-charset="UTF-8">
      <label for="title">やること名</label>
      <input type="text" id="title" name="title" value="${param.title}" />
      <small class="note">※20字以内で入力してください</small>
      <p style="color:red;"><c:out value="${titleError}"/></p>

      <label for="details">詳細</label>
      <textarea id="details" name="details" rows="8" placeholder="ここにやることの詳細を500字以内で入力してください" style="width:100%;font-size:16px;">${param.details}</textarea>
      <p style="color:red;"><c:out value="${detailError}"/></p>

      <label for="scheduleDate">予定日</label>
      <div class="schedule-row">
        <!-- 年 -->
        <select name="scheduleYear" id="yearSelect" class="schedule-select">
          <option value="">年</option>
          <% for (int y = currentYear; y <= currentYear + 10; y++) { %>
            <option value="<%= y %>" <%= (String.valueOf(y).equals(request.getParameter("scheduleYear")) || (request.getParameter("scheduleYear")==null && y==currentYear)) ? "selected" : "" %>><%= y %>年</option>
          <% } %>
        </select>

        <select name="scheduleMonth" id="monthSelect" class="schedule-select">
          <option value="">月</option>
          <% for (int m = 1; m <= 12; m++) { %>
            <option value="<%= m %>" <%= (String.valueOf(m).equals(request.getParameter("scheduleMonth")) || (request.getParameter("scheduleMonth")==null && m==currentMonth)) ? "selected" : "" %>><%= m %>月</option>
          <% } %>
        </select>

        <select name="scheduleDay" id="daySelect" class="schedule-select">
          <option value="">日</option>
          <% for (int d = 1; d <= 31; d++) { %>
            <option value="<%= d %>" <%= (String.valueOf(d).equals(request.getParameter("scheduleDay")) || (request.getParameter("scheduleDay")==null && d==currentDay)) ? "selected" : "" %>><%= d %>日</option>
          <% } %>
        </select>
      </div>
      <p style="color:red;"><c:out value="${scheduleDateError}"/></p>

      <div style="display:flex;justify-content:space-between;margin-top:20px;">
        <a href="<c:url value='/Index'/>" class="button button-blue" style="background-color:#00aaff;color:#fff;">戻る</a>
        <button type="submit" class="button btn-yellow">登録</button>
      </div>
    </form>
  </main>

  <footer><small>© Sample</small></footer>

  <script>
    document.addEventListener("DOMContentLoaded", function () {
      const yearSelect = document.getElementById("yearSelect");
      const monthSelect = document.getElementById("monthSelect");
      const daySelect = document.getElementById("daySelect");

      function updateDayOptions() {
        const today = new Date();
        const y = parseInt(yearSelect.value);
        const m = parseInt(monthSelect.value);
        if (isNaN(y) || isNaN(m)) return;

        const lastDay = new Date(y, m, 0).getDate();
        const selectedDay = parseInt(daySelect.value) || null;

        daySelect.innerHTML = '<option value="">日</option>';
        for (let d = 1; d <= lastDay; d++) {
          const option = document.createElement("option");
          option.value = d;
          option.text = d + "日";

          const selectedDate = new Date(y, m - 1, d);
          const isPast = selectedDate < new Date(today.getFullYear(), today.getMonth(), today.getDate());
          if (isPast) option.disabled = true;

          if (selectedDay === d) option.selected = true;

          daySelect.appendChild(option);
        }
      }

      yearSelect.addEventListener("change", updateDayOptions);
      monthSelect.addEventListener("change", updateDayOptions);
      updateDayOptions();
    });
  </script>
</body>
</html>

