CREATE TABLE `ranking`.`MEMBER`
(
    `member_no`       INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '회원 일련번호',
    `member_name`     VARCHAR(50) NOT NULL COMMENT '회원 이름',
    `score`           INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '점수',
    `insert_date`     DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3) COMMENT '등록일자',
    `insert_operator` VARCHAR(50) NOT NULL COMMENT '등록자',
    `update_date`     DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3) COMMENT '수정일자',
    `update_operator` VARCHAR(50) NOT NULL COMMENT '수정자',
    PRIMARY KEY (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 DEFAULT COLLATE='utf8mb4_general_ci' COMMENT='회원';