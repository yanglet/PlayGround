CREATE TABLE `order-db`.`ORDERS`
(
    `order_no`        INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '주문 일련번호',
    `item_no`       INT UNSIGNED NOT NULL COMMENT '상품 일련번호',
    `member_no`       INT UNSIGNED NOT NULL COMMENT '회원 일련번호',
    `insert_date`     DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3) COMMENT '등록일자',
    PRIMARY KEY (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 DEFAULT COLLATE='utf8mb4_general_ci' COMMENT='주문';