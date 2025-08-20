package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import Model.User;
import Model.UserDAO;

@WebServlet({"/", "/Login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/View/login.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        boolean hasError = false;

        if (id == null || id.length() < 8 || id.length() > 20 || !id.matches("^[0-9]+$")) {
            request.setAttribute("idError", "IDは8〜20桁の半角数字で入力してください");
            hasError = true;
        }
        if (password == null || password.length() < 8 || password.length() > 20 || !password.matches("^[a-zA-Z0-9]+$")) {
            request.setAttribute("passwordError", "パスワードは8〜20桁の半角英数字で入力してください");
            hasError = true;
        }

        if (hasError) {
            request.getRequestDispatcher("/WEB-INF/View/login.jsp").forward(request, response);
            return;
        }

        UserDAO dao = new UserDAO();
        User user = dao.findUserById(id);

        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/Index");
        } else {
            request.setAttribute("error", "IDまたはパスワードが違います。");
            request.getRequestDispatcher("/WEB-INF/View/login.jsp").forward(request, response);
        }
    }
}
