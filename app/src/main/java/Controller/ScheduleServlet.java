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

@WebServlet("/Schedule")
public class ScheduleServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.getRequestDispatcher("/WEB-INF/View/schedule.jsp").forward(request, response);
  }

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

    String title = request.getParameter("title");
    String detail = request.getParameter("details");
    String year = request.getParameter("scheduleYear");
    String month = request.getParameter("scheduleMonth");
    String day = request.getParameter("scheduleDay");

    Task task = new Task();
    task.setUserId(user.getId());

    boolean hasError = false;

    if (title == null || title.isEmpty()) {
      request.setAttribute("titleError", "やることを入力してください");
      hasError = true;
    } else if (title.length() > 20) {
      request.setAttribute("titleError", "やることは20文字以内で入力してください");
      hasError = true;
    } else {
      task.setTitle(title);
    }

    if (detail == null) detail = "";
    if (detail.length() > 500) {
      request.setAttribute("detailError", "詳細は500文字以内で入力してください");
      hasError = true;
    }
    task.setDetail(detail);

    java.sql.Date scheduleDate = null;
    if (year == null || month == null || day == null ||
        year.isEmpty() || month.isEmpty() || day.isEmpty()) {
      request.setAttribute("scheduleDateError", "スケジュール日を正しく選択してください");
      hasError = true;
    } else {
      try {
        String dateStr = String.format("%s-%02d-%02d",
            Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        scheduleDate = java.sql.Date.valueOf(dateStr);

        java.time.LocalDate today = java.time.LocalDate.now();
        if (scheduleDate.toLocalDate().isBefore(today)) {
          request.setAttribute("scheduleDateError", "過去の日付は選択できません");
          hasError = true;
        }
      } catch (Exception e) {
        request.setAttribute("scheduleDateError", "日付の形式が正しくありません");
        hasError = true;
      }
    }
    if (!hasError) task.setScheduleDate(scheduleDate);

    if (hasError) {
      request.getRequestDispatcher("/WEB-INF/View/schedule.jsp").forward(request, response);
      return;
    }

    TaskDAO dao = new TaskDAO();
    boolean result = dao.insertTask(task);

    if (result) {
      response.sendRedirect(request.getContextPath() + "/Index?registered=true");
    } else {
      request.setAttribute("error", "タスクの登録に失敗しました。");
      request.getRequestDispatcher("/WEB-INF/View/schedule.jsp").forward(request, response);
    }
  }
}
