package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.TaskDAO;
import Model.User;

@WebServlet("/DeleteTask")
public class DeleteTaskServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        String idParam = request.getParameter("id");

        if (idParam != null) {
            try {
                long id = Long.parseLong(idParam);

                TaskDAO dao = new TaskDAO();
                boolean result = dao.deleteTask(id);

                if (result) {
                    response.sendRedirect(request.getContextPath() + "/List");
                } else {
                    request.setAttribute("error", "削除に失敗しました。");
                    request.getRequestDispatcher("/WEB-INF/View/list.jsp").forward(request, response);
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/List");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/List");
        }
    }
}


