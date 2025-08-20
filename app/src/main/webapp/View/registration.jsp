<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>会員登録</title>
  <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
  <header>
    <div class="header-left">会員登録</div>
    <div class="header-right">
      <a href="<c:url value='/'/>">ログインはこちら</a>
    </div>
  </header>

  <main>
    <form class="form" action="<c:url value='/Registration'/>" method="post" accept-charset="UTF-8">
      <label for="name">名前</label>
      <input type="text" id="name" name="name" value="${param.name}">
      <p style="color:red;"><c:out value="${nameError}"/></p>

      <label for="id">ID</label>
      <input type="text" id="id" name="id" value="${param.id}">
      <p style="color:red;"><c:out value="${idError}"/></p>

      <label for="password">パスワード</label>
      <input type="password" id="password" name="password">
      <p style="color:red;"><c:out value="${passwordError}"/></p>

      <p style="color:red;"><c:out value="${error}"/></p>

      <button type="submit" class="login-button">登録</button>
    </form>
  </main>

  <footer>
    <small>© Sample</small>
  </footer>
</body>
</html>
