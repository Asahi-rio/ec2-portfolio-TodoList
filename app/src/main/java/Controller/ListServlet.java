package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Task;
import Model.TaskDAO;
import Model.User;

@WebServlet("/List")
public class ListServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    HttpSession session = request.getSession(false);
    User user = (session != null) ? (User) session.getAttribute("user") : null;

    if (user == null) {
      response.sendRedirect(request.getContextPath() + "/");
      return;
    }

    TaskDAO dao = new TaskDAO();
    List<Task> taskList = dao.getTasksByUserId(user.getId());
    request.setAttribute("taskList", taskList);

    request.getRequestDispatcher("/WEB-INF/View/list.jsp").forward(request, response);
  }
}
