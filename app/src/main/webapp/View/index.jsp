<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>マイページ</title>
  <!-- CSSもc:urlで -->
  <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>

  <header>
    <div class="header-left">マイページ</div>
    <div class="header-right">
      ようこそ、<c:out value="${sessionScope.user.name}"/>さん！
    </div>
  </header>

  <c:if test="${registerSuccess}">
    <div id="flash-message" style="
      position: fixed; top: 20px; left: 50%; transform: translateX(-50%);
      background-color: #dff0d8; color: #3c763d; padding: 15px 30px;
      border-radius: 8px; box-shadow: 0px 2px 6px rgba(0,0,0,0.3);
      z-index: 9999; font-weight: bold; font-size: 16px;">
      やること登録が完了しました
    </div>
    <script>
      setTimeout(() => {
        const msg = document.getElementById("flash-message");
        if (msg) msg.style.display = "none";
      }, 3000);
    </script>
  </c:if>

  <main>
    <div class="left-box">
      <h2><c:out value="${today}"/><br>本日のやることは？</h2>

      <div class="todo-box">
        <c:choose>
          <c:when test="${not empty todoList}">
            <c:forEach var="task" items="${todoList}">
              <c:out value="${task}"/><br/>
            </c:forEach>
          </c:when>
          <c:otherwise>やることはありません。</c:otherwise>
        </c:choose>
      </div>
    </div>

    <div class="right-box">
      <a href="<c:url value='/Schedule'/>" class="button btn-yellow">やること登録</a>
      <a href="<c:url value='/List'/>" class="button btn-yellow">やること一覧</a>
      <a href="<c:url value='/Logout'/>" class="button btn-red">ログアウト</a>
    </div>
  </main>

  <footer>
    <small>© Sample</small>
  </footer>
</body>
</html>
