package Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.TaskDAO;
import Model.User;

@WebServlet("/Index")
public class IndexServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    HttpSession session = request.getSession(false);
    User user = (session != null) ? (User) session.getAttribute("user") : null;

    if (user == null) {
      response.sendRedirect(request.getContextPath() + "/");
      return;
    }

    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M月d日");
    request.setAttribute("today", today.format(formatter));

    TaskDAO dao = new TaskDAO();
    List<String> todoList = dao.findTasksByDate(user.getId(), today);
    request.setAttribute("todoList", todoList);

    if ("true".equals(request.getParameter("registered"))) {
      request.setAttribute("registerSuccess", true);
    }

    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/View/index.jsp");
    rd.forward(request, response);
  }
}

