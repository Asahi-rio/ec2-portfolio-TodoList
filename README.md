## 制作の目的



## ディレクトリ
ec2-portfolio-TodoList/
├─ app/
│  ├─ pom.xml
│  └─ src/main/
│     ├─ java/
│     │  ├─ Controller/
│     │  │  ├─ DeleteTaskServlet.java
│     │  │  ├─ DetailPageServlet.java
│     │  │  ├─ IndexServlet.java
│     │  │  ├─ ListServlet.java
│     │  │  ├─ LoginServlet.java
│     │  │  ├─ LogoutServlet.java
│     │  │  ├─ RegistrationServlet.java
│     │  │  ├─ ScheduleServlet.java
│     │  │  └─ HealthServlet.java
│     │  └─ Model/
│     │     ├─ DBconnector.java
│     │     ├─ User.java
│     │     ├─ UserDAO.java
│     │     ├─ Task.java
│     │     └─ TaskDAO.java
│     └─ webapp/
│        ├─ View/
│        │  ├─ detailpage.jsp
│        │  ├─ index.jsp
│        │  ├─ list.jsp
│        │  ├─ login.jsp
│        │  ├─ registration.jsp
│        │  └─ schedule.jsp
│        └─ WEB-INF/web.xml
├─ sql/
| ├─ schema.sql
| └─ seed_sample.sql
└─ config/
└─ setenv.sh.sample
