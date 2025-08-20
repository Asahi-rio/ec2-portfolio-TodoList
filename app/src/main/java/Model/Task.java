package Model;

import java.sql.Date;
import java.sql.Timestamp;

public class Task {
    private long id;
    private String userId;
    private String titleText;
    private String detail;
    private Timestamp createdAt;
    private Date scheduleDate; 

    // コンストラクタ（全フィールド）
    public Task(long id, String userId, String titleText, String detail, Timestamp createdAt, Date scheduleDate) {
        this.id = id;
        this.userId = userId;
        this.titleText = titleText;
        this.detail = detail;
        this.createdAt = createdAt;
        this.scheduleDate = scheduleDate;
    }
    
    // 引数なし
    public Task() {}

    //新規登録用
    public Task(String userId, String doText, String detail, Date scheduleDate) {
        this.userId = userId;
        this.titleText = doText;
        this.detail = detail;
        this.scheduleDate = scheduleDate;
    }

    // getter・setter
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getTitle() { return titleText; }
    public void setTitle(String doText) { this.titleText = doText; }

    public String getDetail() { return detail; }
    public void setDetail(String detail) { this.detail = detail; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
    
    public Date getScheduleDate() { return scheduleDate;}
    public void setScheduleDate(Date scheduleDate) {this.scheduleDate = scheduleDate;}
}

