-- MySQL dump 10.13  Distrib 8.0.43, for Linux (x86_64)
--
-- Host: todoapp-db.chqia2essngh.ap-northeast-1.rds.amazonaws.com    Database: todoApp
-- ------------------------------------------------------
-- Server version	8.0.41

DROP TABLE IF EXISTS `task`;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id`         VARCHAR(20)  NOT NULL,
  `name`       VARCHAR(50)  NOT NULL,
  `password`   VARCHAR(255) NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `task` (
  `id`            BIGINT NOT NULL AUTO_INCREMENT,
  `user_id`       VARCHAR(20)  NOT NULL,
  `title`         VARCHAR(100) NOT NULL,
  `detail`        VARCHAR(500) DEFAULT NULL,
  `schedule_date` DATE         DEFAULT NULL,
  `created_at`    TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_task_user` (`user_id`),
  CONSTRAINT `fk_user` FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
