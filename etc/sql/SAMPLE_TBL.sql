CREATE TABLE `SAMPLE_TB` (
    `sample_pid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '샘플_일련번호',
    `sample_code` varchar(7) NOT NULL COMMENT '샘플_코드',
    `sample_nm` varchar(50) NOT NULL COMMENT '샘플_이름',
    `sample_cntnts` varchar(2000) NOT NULL COMMENT '샘플_내용',
    `writer_id` varchar(100) NOT NULL COMMENT '작성자_아이디',
    `writer_nm` varchar(100) NOT NULL COMMENT '작성자_이름',
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성_시간',
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정_시간',
    PRIMARY KEY (`menu_pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4