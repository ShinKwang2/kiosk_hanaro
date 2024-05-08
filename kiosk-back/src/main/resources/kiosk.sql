USE kiosk;
drop table if exists `product_option`;

drop table if exists `order_product`;
drop table if exists `product`;
drop table if exists `orders`;
drop table if exists `users`;
CREATE TABLE `users` (
                         `user_id` int NOT NULL AUTO_INCREMENT,
                         `phone_number` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                         `user_point` int DEFAULT 0,
                         `user_created_date` datetime(6) DEFAULT CURRENT_TIMESTAMP(6),
                         PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



CREATE TABLE `product` (
                           `product_id` int NOT NULL AUTO_INCREMENT,
                           `product_name_kr` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                           `product_name_en` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                           `product_contents_kr` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                           `product_contents_en` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                           `product_price` int NOT NULL,
                           `product_img_original` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                           `product_img_url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                           `product_list_img_url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                           `product_status` enum('SELLABLE','SOLD_OUT') COLLATE utf8mb4_unicode_ci NOT NULL,
                           `product_type` enum('HAMBURGER','MC_MORNING','DRINK','DESSERT') COLLATE utf8mb4_unicode_ci NOT NULL,
                           `product_created_date` datetime(6) DEFAULT CURRENT_TIMESTAMP(6),
                           `product_updated_date` datetime(6) DEFAULT CURRENT_TIMESTAMP(6),
                           PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `product_option` (
                                  `product_option_id` int NOT NULL AUTO_INCREMENT,
                                  `option_type` enum('SINGLE','WITH_COKE','SET') COLLATE utf8mb4_unicode_ci NOT NULL,
                                  `add_price` int NOT NULL,
                                  `product_id` int NOT NULL,
                                  PRIMARY KEY (`product_option_id`),
                                  CONSTRAINT `fk_product_option_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `orders` (
                          `order_id` int NOT NULL AUTO_INCREMENT,
                          `order_status` enum('INIT','RECEIVED','PAYMENT_COMPLETED','PAYMENT_FAILED','CANCELED','COMPLETED') COLLATE utf8mb4_unicode_ci NOT NULL,
                          `user_id` int DEFAULT NULL,
                          `order_created_date` datetime(6) DEFAULT CURRENT_TIMESTAMP(6),
                          `order_updated_date` datetime(6) DEFAULT CURRENT_TIMESTAMP(6),
                          PRIMARY KEY (`order_id`),
                          CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `order_product` (
                                 `order_product_id` int NOT NULL AUTO_INCREMENT,
                                 `order_id` int NOT NULL,
                                 `product_id` int NOT NULL,
                                 `order_product_price` int NOT NULL,
                                 `order_product_quantity` int NOT NULL,
                                 PRIMARY KEY (`order_product_id`),
                                 CONSTRAINT `fk_order_product_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
                                 CONSTRAINT `fk_order_product_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



INSERT INTO product(product_name_kr, product_name_en, product_contents_kr, product_contents_en, product_price,
                    product_img_original, product_img_url, product_list_img_url,
                    product_status, product_type, product_created_date, product_updated_date)
VALUES ('빅맥®BLT', 'Big Mac®BLT',
        '한 입에 가득 차는 100% 순쇠고기 패티에 신선한 토마토와 고소한 베이컨을 추가하여 더 특별하게!',
        '100% pure beef patty with fresh tomatoes and bacon to make it even more special!',
        7400, 'https://www.mcdonalds.co.kr/upload/product/pcList/1712034569422.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1712034569422.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1712034569422.png',
        'SELLABLE', 'HAMBURGER', DEFAULT , DEFAULT
       ),
       ('더블 빅맥', 'Double Big Mac',
        '한 입에 가득 차는 100% 순 쇠고기 패티 4장의 진한 육즙과 풍미가 입안 가득!',
        'With four 100% real beef patties, enjoy the deep taste and flavor of the juicy beef patties that fill your mouth!',
        7600, 'https://www.mcdonalds.co.kr/upload/product/pcList/1712034721611.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1712034721611.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1712034721611.png',
        'SELLABLE', 'HAMBURGER', DEFAULT, DEFAULT
       ),
       ('더블 쿼터파운더® 치즈', 'Double Quarter Pounder® with Cheese',
        '좋아하는건 더 많이 즐기시라고, <br>두 배 이상 커진 1/4 파운드<br>\r\n비프 패티가 두 장 (226그램)! 육즙이 풍부한 고기 맛을 그대로 살린 100% 순 쇠고기 패티 두 장과 치즈 두 장이 입안 가득 완벽하게 조화되는 놀라운 맛',
        'Double up the enjoyment with double quarter pound beef patties. Specially selected juicy, prime 100% pure beef patties simply seasoned with original taste and two slices of melty cheese.',
        8200, 'https://www.mcdonalds.co.kr/upload/product/pcList/1583727487454.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1583727487454.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1583727487454.png',
        'SELLABLE', 'HAMBURGER', DEFAULT, DEFAULT
       ),
       ('쿼터파운더® 치즈', 'Quarter Pounder® with Cheese',
        '쿼터파운더라는 이름에서 알 수 있듯이 두 배 이상 커진 1/4파운드(113그램) 비프와 부드러운 치즈 두 장의 환상궁합! 두툼한 순 쇠고기 패티와 신선한 치즈의 풍부한 맛으로 세계적으로 사랑받고 있는 맥도날드의 대표적인 프리미엄 버거',
        'A quarter pound of juicy, 100% beef simply seasoned with a pinch of salt and pepper, two slices of melty cheese, slivered onions and tangy pickles all on a sesame seed bun.',
        6300, 'https://www.mcdonalds.co.kr/upload/product/pcList/1583728183515.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1583728183515.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1583728183515.png',
        'SELLABLE', 'HAMBURGER', DEFAULT, DEFAULT
       ),
       ('자두 천도복숭아 칠러', 'Plum Peach Chiller',
        '상콤한 자두와 달콤한 천도복숭아의 만남! 자두 천도복숭아 칠러',
        'A meeting between a fresh plum and a sweet Peach! Plum Peach Chiller',
        3700, 'https://www.mcdonalds.co.kr/upload/product/pcList/1698851151138.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1698851151138.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1698851151138.png',
        'SELLABLE', 'DRINK', DEFAULT, DEFAULT
       ),
       ('제주 한라봉 칠러', 'Jeju Hallabong Chiller',
        '제주 한라봉의 상큼새콤한 맛과 향을 즐겨보세요!',
        'Enjoy the refreshing taste and aroma of Jeju Hallabong!',
        3700, 'https://www.mcdonalds.co.kr/upload/product/pcList/1681287171454.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1681287171454.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1681287171454.png',
        'SELLABLE', 'DRINK', DEFAULT, DEFAULT
       ),
       ('바닐라 라떼', 'Vanilla Latte',
        '바로 내린 100% 친환경 커피의 진한 맛과 향, 1A등급 우유, 그리고 천연 바닐라 향으로 달콤함까지!',
        'The strong taste and aroma of 100% eco-friendly coffee, 1A-grade milk and sweet natural vanilla flavor!',
        4500, 'https://www.mcdonalds.co.kr/upload/product/pcList/1677678747710.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1677678747710.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1677678747710.png',
        'SELLABLE', 'DRINK', DEFAULT, DEFAULT
       ),
       ('맥윙™', 'McWings™',
        '겉은 바삭, 속은 쫄깃! 치킨 땡길 땐 맥윙!',
        'Flavorful chicken wings with crispy on the outside and chewy on the inside!',
        4200, 'https://www.mcdonalds.co.kr/upload/product/pcList/1712028381366.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1712028381366.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1712028381366.png',
        'SELLABLE', 'DESSERT', DEFAULT, DEFAULT
       ),
       ('맥윙™ 콤보', 'McWings™ combo',
        '겉은 바삭! 속은 쫄~깃한 치킨 윙! 그리고 시원한 탄산음료!',
        'Flavorful chicken wings with crispy on the outside and chewy on the inside!',
        7100, 'https://www.mcdonalds.co.kr/upload/product/pcList/1712028667694.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1712028667694.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1712028667694.png',
        'SELLABLE', 'DESSERT', DEFAULT, DEFAULT
       ),
       ('코울슬로', 'Coleslaw',
        '양배추, 당근, 양파가 상큼하고 크리미한 마요 드레싱과 어우러져 아삭하게 씹히는 샐러드',
        'A crunchy salad with cabbage, carrots and onions combined <br>with a creamy mayonnaise dressing',
        2700, 'https://www.mcdonalds.co.kr/upload/product/pcList/1676460052498.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1676460052498.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1676460052498.png',
        'SELLABLE', 'DESSERT', DEFAULT, DEFAULT
       );


INSERT INTO product_option(option_type, add_price, product_id)
VALUES ('SINGLE', 0, 1), ('WITH_COKE', 900, 1), ('SET', 1600, 1),
       ('SINGLE', 0, 2), ('WITH_COKE', 900, 2), ('SET', 1600, 2),
       ('SINGLE', 0, 3), ('WITH_COKE', 900, 3), ('SET', 1600, 4),
       ('SINGLE', 0, 4), ('WITH_COKE', 900, 4), ('SET', 1600, 4),
       ('SINGLE', 0, 5), ('SINGLE', 0, 6), ('SINGLE', 0, 7), ('SINGLE', 0, 8), ('SINGLE', 0, 9), ('SINGLE', 0, 10);


