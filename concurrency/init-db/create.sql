CREATE TABLE `concurrency`.`ITEM`
(
    `ITEM_NO`         INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '상품일련번호',
    `ITEM_NAME`       VARCHAR(100) NOT NULL COMMENT '상품 이름',
    `ITEM_QUANTITY`   TINYINT NOT NULL COMMENT '상품 개수',
--     `VERSION`         INT UNSIGNED NOT NULL DEFAULT 0 COMMENT 'version',
    `INSERT_DATE`     DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3) COMMENT '등록일자',
    `INSERT_OPERATOR` VARCHAR(50)  NOT NULL COMMENT '등록자',
    `UPDATE_DATE`     DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3) ON UPDATE CURRENT_TIMESTAMP (3) COMMENT '수정일자',
    `UPDATE_OPERATOR` VARCHAR(50)  NOT NULL COMMENT '수정자',
    PRIMARY KEY (`ITEM_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 DEFAULT COLLATE='utf8mb4_general_ci' COMMENT='상품';