package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Task;
import Model.TaskDAO;
import Model.User;

@WebServlet("/Detail")
public class DetailPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        String idStr = request.getParameter("id");
        if (idStr == null) {
            response.sendRedirect(request.getContextPath() + "/List");
            return;
        }

        try {
            long id = Long.parseLong(idStr);
            TaskDAO dao = new TaskDAO();
            Task task = dao.getTaskById(id);

            if (task != null) {
                request.setAttribute("task", task); // Taskオブジェクトを丸ごと渡す
                request.getRequestDispatcher("/WEB-INF/View/detailpage.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/List");
            }

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/List");
        }
    }
}

