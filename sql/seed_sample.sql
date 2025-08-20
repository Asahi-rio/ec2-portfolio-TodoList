-- demo users（ダミー値。メールはexample.com、パスワードはダミーハッシュ）
INSERT INTO users (id, email, password_hash, created_at) VALUES
(1, 'demo@example.com', '$2b$10$aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', NOW());

-- demo tasks（最小限）
INSERT INTO tasks (id, user_id, subject, done, created_at) VALUES
(1, 1, '最初のタスク', 0, NOW()),
(2, 1, 'サンプル: DBに接続', 0, NOW());
