package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List; 

public class TaskDAO {

    // タスクを登録するメソッド
    public boolean insertTask(Task task) {
        boolean result = false;

        try (Connection conn = DBconnector.getConnection()) {
            String sql = "INSERT INTO task (user_id, title, detail, schedule_date) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, task.getUserId());
            pstmt.setString(2, task.getTitle());
            pstmt.setString(3, task.getDetail());
            pstmt.setDate(4, task.getScheduleDate()); 

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // タスクを削除するメソッド（主キーのidで指定）
    public boolean deleteTask(long id) {
        boolean result = false;

        try (Connection conn = DBconnector.getConnection()) {
            String sql = "DELETE FROM task WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public List<Task> getTasksByUserId(String userId) {
        List<Task> taskList = new ArrayList<>();

        try (Connection conn = DBconnector.getConnection()) {
            String sql = "SELECT * FROM task WHERE user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getLong("id"));
                task.setUserId(rs.getString("user_id"));
                task.setTitle(rs.getString("title"));
                task.setDetail(rs.getString("detail")); 
                task.setCreatedAt(rs.getTimestamp("created_at")); 
                task.setScheduleDate(rs.getDate("schedule_date")); 
                taskList.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return taskList;
    }
    
 // 特定のタスクをIDで取得するメソッド
    public Task getTaskById(long id) {
        Task task = null;

        try (Connection conn = DBconnector.getConnection()) {
            String sql = "SELECT * FROM task WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                task = new Task();
                task.setId(rs.getLong("id"));
                task.setUserId(rs.getString("user_id"));
                task.setTitle(rs.getString("title"));
                task.setDetail(rs.getString("detail"));
                task.setScheduleDate(rs.getDate("schedule_date")); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return task;
    }
    
    //アプリを開いている当日のタスクのみを表示する
    public List<String> findTasksByDate(String userId, LocalDate date) {
        List<String> list = new ArrayList<>();
        String sql = "SELECT title FROM task WHERE user_id = ? AND DATE(schedule_date) = ?";

        try (Connection conn = DBconnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userId);
            stmt.setDate(2, java.sql.Date.valueOf(date));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("title"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }



}


