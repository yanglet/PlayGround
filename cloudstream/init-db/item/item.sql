CREATE TABLE `item-db`.`ITEM`
(
    `item_no`         INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '상품 일련번호',
    `member_no`       INT UNSIGNED NOT NULL COMMENT '회원 일련번호',
    `quantity`        INT UNSIGNED NOT NULL COMMENT '재고',
    `insert_date`     DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3) COMMENT '등록일자',
    PRIMARY KEY (`item_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 DEFAULT COLLATE='utf8mb4_general_ci' COMMENT='상품';

INSERT INTO `item-db`.`ITEM`(member_no, quantity, insert_date)
VALUES (1, 10, now());