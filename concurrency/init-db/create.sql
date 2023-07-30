CREATE TABLE `concurrency`.`ITEM`
(
    `item_no`         INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '상품일련번호',
    `item_name`       VARCHAR(100) NOT NULL COMMENT '상품 이름',
    `item_quantity`   TINYINT NOT NULL COMMENT '상품 개수',
    `insert_date`     DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3) COMMENT '등록일자',
    `insert_operator` VARCHAR(50)  NOT NULL COMMENT '등록자',
    `update_date`     DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3) ON UPDATE CURRENT_TIMESTAMP (3) COMMENT '수정일자',
    `update_operator` VARCHAR(50)  NOT NULL COMMENT '수정자',
    PRIMARY KEY (`item_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 DEFAULT COLLATE='utf8mb4_general_ci' COMMENT='상품';

CREATE TABLE `concurrency`.`OPTIMISTIC_LOCK_ITEM`
(
    `item_no`         INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '상품일련번호',
    `item_name`       VARCHAR(100) NOT NULL COMMENT '상품 이름',
    `item_quantity`   TINYINT NOT NULL COMMENT '상품 개수',
    `version`         INT UNSIGNED NOT NULL DEFAULT 0 COMMENT 'version',
    `insert_date`     DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3) COMMENT '등록일자',
    `insert_operator` VARCHAR(50)  NOT NULL COMMENT '등록자',
    `update_date`     DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3) ON UPDATE CURRENT_TIMESTAMP (3) COMMENT '수정일자',
    `update_operator` VARCHAR(50)  NOT NULL COMMENT '수정자',
    PRIMARY KEY (`item_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 DEFAULT COLLATE='utf8mb4_general_ci' COMMENT='상품';

CREATE TABLE `concurrency`.`ATOMIC_ITEM`
(
    `item_no`         INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '상품일련번호',
    `item_name`       VARCHAR(100) NOT NULL COMMENT '상품 이름',
    `item_quantity`   TINYINT NOT NULL COMMENT '상품 개수',
    `insert_date`     DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3) COMMENT '등록일자',
    `insert_operator` VARCHAR(50)  NOT NULL COMMENT '등록자',
    `update_date`     DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3) ON UPDATE CURRENT_TIMESTAMP (3) COMMENT '수정일자',
    `update_operator` VARCHAR(50)  NOT NULL COMMENT '수정자',
    PRIMARY KEY (`item_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 DEFAULT COLLATE='utf8mb4_general_ci' COMMENT='상품';