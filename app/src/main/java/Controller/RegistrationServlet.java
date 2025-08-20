package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import Model.User;
import Model.UserDAO;

@WebServlet("/Registration")
public class RegistrationServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.getRequestDispatcher("/WEB-INF/View/registration.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");

    String id = request.getParameter("id");
    String name = request.getParameter("name");
    String password = request.getParameter("password");

    boolean hasError = false;

    if (id == null || id.length() < 8 || id.length() > 20 || !id.matches("^[0-9]+$")) {
      request.setAttribute("idError", "IDは8〜20桁の半角数字で入力してください");
      hasError = true;
    }
    if (name == null || name.isEmpty()) {
      request.setAttribute("nameError", "名前を入力してください");
      hasError = true;
    } else if (name.length() > 50) {
      request.setAttribute("nameError", "名前は50文字以内で入力してください");
      hasError = true;
    }
    if (password == null || password.length() < 8 || password.length() > 20
        || !password.matches("^[a-zA-Z0-9]+$")) {
      request.setAttribute("passwordError", "パスワードは8〜20桁の半角英数字で入力してください");
      hasError = true;
    }

    if (hasError) {
      request.getRequestDispatcher("/WEB-INF/View/registration.jsp").forward(request, response);
      return;
    }

    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
    User user = new User(id, name, hashedPassword);

    UserDAO dao = new UserDAO();
    boolean result = dao.insertUser(user);

    if (result) {
      // 重要：リダイレクトは必ずコンテキストパス付き！
      response.sendRedirect(request.getContextPath() + "/");
    } else {
      request.setAttribute("error", "このIDは既に登録されています。");
      request.getRequestDispatcher("/WEB-INF/View/registration.jsp").forward(request, response);
    }
  }
}



