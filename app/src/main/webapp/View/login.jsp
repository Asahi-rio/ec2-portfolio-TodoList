<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>ログイン画面</title>
  <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
  <header>
    <div class="header-left">ログイン画面</div>
    <div class="header-right">
      <a href="<c:url value='/Registration'/>">会員登録はコチラ</a>
    </div>
  </header>

  <main>
    <form class="form" action="<c:url value='/Login'/>" method="post" accept-charset="UTF-8">
      <label for="id">ID</label>
      <input type="text" id="id" name="id" value="">
      <p style="color: red;"><c:out value='${idError}'/></p>

      <p style="color: red;"><c:out value='${error}'/></p>

      <label for="password">パスワード</label>
      <input type="password" id="password" name="password" value="">
      <p style="color: red;"><c:out value='${passwordError}'/></p>

      <button type="submit" class="login-button">ログイン</button>
    </form>
  </main>

  <footer>
    <small>© Sample</small>
  </footer>
</body>
</html>


