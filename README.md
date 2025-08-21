## アプリケーション概要
Java（Servlet/JSP）とMySQLを用いたシンプルなTodoアプリケーションです。<br>
ユーザーは タスクの登録・表示・削除 を行うことができ、会員登録やログイン機能も備えています。<br>

本番環境では以下のAWSサービスを利用し、インフラ設計を意識した構成でデプロイしました。<br>
・VPC：パブリック/プライベートサブネットを分離し、セキュアなネットワークを構築<br>
・EC2 (Amazon Linux 2023)：Tomcat 9 / Java 11 を用いたアプリケーションサーバー<br>
・RDS (MySQL)：アプリケーションのデータベース<br>
・ALB (Application Load Balancer)：冗長化を見据えたロードバランサー構成<br>
・Route 53：独自ドメインを取得し、ALBに接続<br>

## 作成の目的
・クラウド環境でのアプリケーション構築を通じて、インフラ設計からデプロイまで一連の流れを体験し、インフラの基礎を身につける<br>
・セキュリティや可用性を意識した設計（VPCの分離、ALBによる冗長化、最小限のセキュリティグループ設定など）を実際に構築し、実務で必要とされる知識を学ぶ<br>
・アプリケーション開発だけでなく、Linux環境でのミドルウェア設定やAWSサービスの組み合わせを経験し、幅広いスキルを習得する<br>

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

## 工夫した点
①可用性を意識した構成<br>
・VPCを利用し、パブリックサブネットとプライベートサブネットを分離。ALBを配置することで、将来的な冗長化やスケールアウトを可能にする拡張性を意識しました。<br>
②セキュリティを重視した設計<br>
・EC2やRDSはプライベートサブネットに設置し、外部から直接アクセスできない構成に。セキュリティグループも最小限の通信だけを許可し、安全性を高めました。<br>
③運用を見据えた構築<br>
・Route53で独自ドメインを取得し、ALBと組み合わせて運用しやすい環境を整備。環境変数や設定ファイルを分離し、認証情報をソースコードに含めない工夫をしました。<br>
④アプリケーション面での工夫<br>
サーバーサイドではMVCモデルを意識し、コードの役割を明確に分離。フロントエンドも自作し、ログイン画面やタスク管理画面をユーザーが直感的に操作できるよう設計しました。<br>
