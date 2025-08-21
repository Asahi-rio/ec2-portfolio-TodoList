## アプリケーション概要
本アプリケーションは **Java（Servlet/JSP）とMySQLを用いたシンプルなTodoアプリ** です。<br>
ユーザーは **タスクの登録・表示・削除** を行うことができ、会員登録やログイン機能も備えています。  

本番環境では以下のAWSサービスを利用し、インフラ設計を意識した構成でデプロイしました。  
- **VPC**：パブリック/プライベートサブネットを分離し、セキュアなネットワークを構築  
- **EC2 (Amazon Linux 2023)**：Tomcat 9 / Java 11 を用いたアプリケーションサーバー  
- **RDS (MySQL)**：アプリケーションのデータベース  
- **ALB (Application Load Balancer)**：冗長化を見据えたロードバランサー構成  
- **Route 53**：独自ドメインを取得し、ALBに接続  

---

## 作成の目的
- **クラウド環境でのアプリ構築を体験**  
  インフラ設計からデプロイまで一連の流れを学習し、インフラの基礎スキルを身につけるため。  

- **セキュリティと可用性の学習**  
  VPC分離・ALB冗長化・最小限のセキュリティグループ設定を実際に構築し、実務で必要な知識を習得するため。  

- **幅広いスキルの習得**  
  アプリ開発だけでなく、Linux環境でのミドルウェア設定やAWSサービスの組み合わせを経験するため。  

---

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
│  ├─ schema.sql
│  └─ seed_sample.sql
└─ config/
   └─ setenv.sh.sample

## 工夫した点
- **可用性を意識した構成**  
  VPCでパブリック/プライベートを分離し、ALBにより冗長化やスケールアウト可能な設計を実現。
   
- **セキュリティ重視の設計**  
  EC2やRDSをプライベートサブネットに設置し、外部アクセスを制限。最小限の通信のみを許可。
  
- **運用を見据えた構築**  
  Route53で独自ドメインを設定。環境変数や設定ファイルを分離し、認証情報をソースに含めない工夫を実施。
  
- **アプリケーション面での工夫**  
  サーバーサイドはMVCモデルを意識して役割を明確化。フロントは直感的に操作できるUIを設計。
  
---

  ## 本ポートフォリオで習得したスキルと実績
- **Linuxサーバの構築・運用経験**  
  - Amazon Linux 2023 上に Java 11 / Tomcat 9 環境を構築  
  - CLI を用いたパッケージ管理・ユーザ権限設定・ディレクトリ管理を実施  
  - コスト最適化のため、EC2 を必要時のみ起動・停止する運用を実践  

- **サーバ・ネットワークの設計構築経験**  
  - VPC / サブネット / ルーティングを設計し、アプリケーションサーバと DB サーバを分離  
  - ALB（Application Load Balancer）＋ Route53 による独自ドメイン運用・冗長化対応  
  - セキュリティグループを用いた http / https / ssh の通信制御を設計  

- **成長意欲・学習実績**  
  - AWS 認定クラウドプラクティショナー資格を取得（2025年6月）  
  - Java（Servlet/JSP）＋ MySQL を用いた Web アプリケーション開発を実践  
  - インフラ設計〜アプリケーション開発〜デプロイまで一連の流れを独力で構築
  
