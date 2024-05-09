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
                         `is_removed` boolean DEFAULT false,
                         `user_created_date` datetime(6) DEFAULT CURRENT_TIMESTAMP(6),
                         `deleted_at` datetime(6) DEFAULT CURRENT_TIMESTAMP(6),
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
        'SELLABLE', 'HAMBURGER', '2023-05-03' , '2023-05-03'
       ),
       ('더블 빅맥', 'Double Big Mac',
        '한 입에 가득 차는 100% 순 쇠고기 패티 4장의 진한 육즙과 풍미가 입안 가득!',
        'With four 100% real beef patties, enjoy the deep taste and flavor of the juicy beef patties that fill your mouth!',
        7600, 'https://www.mcdonalds.co.kr/upload/product/pcList/1712034721611.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1712034721611.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1712034721611.png',
        'SELLABLE', 'HAMBURGER', '2023-05-03', '2023-05-03'
       ),
       ('더블 쿼터파운더® 치즈', 'Double Quarter Pounder® with Cheese',
        '좋아하는건 더 많이 즐기시라고, 두 배 이상 커진 1/4 파운드 비프 패티가 두 장 (226그램)! 육즙이 풍부한 고기 맛을 그대로 살린 100% 순 쇠고기 패티 두 장과 치즈 두 장이 입안 가득 완벽하게 조화되는 놀라운 맛',
        'Double up the enjoyment with double quarter pound beef patties. Specially selected juicy, prime 100% pure beef patties simply seasoned with original taste and two slices of melty cheese.',
        8200, 'https://www.mcdonalds.co.kr/upload/product/pcList/1583727487454.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1583727487454.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1583727487454.png',
        'SELLABLE', 'HAMBURGER', '2019-05-30', '2019-05-30'
       ),
       ('쿼터파운더® 치즈', 'Quarter Pounder® with Cheese',
        '쿼터파운더라는 이름에서 알 수 있듯이 두 배 이상 커진 1/4파운드(113그램) 비프와 부드러운 치즈 두 장의 환상궁합! 두툼한 순 쇠고기 패티와 신선한 치즈의 풍부한 맛으로 세계적으로 사랑받고 있는 맥도날드의 대표적인 프리미엄 버거',
        'A quarter pound of juicy, 100% beef simply seasoned with a pinch of salt and pepper, two slices of melty cheese, slivered onions and tangy pickles all on a sesame seed bun.',
        6300, 'https://www.mcdonalds.co.kr/upload/product/pcList/1583728183515.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1583728183515.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1583728183515.png',
        'SELLABLE', 'HAMBURGER', '2019-05-30', '2019-05-30'
       ),
       ('빅맥®', 'Big Mac®',
        '100% 순 쇠고기 패티 두 장에 빅맥®만의  특별한 소스. 입안에서 살살 녹는 치즈와 신선한 양상추, 양파, 그리고 피클까지.50년 넘게 전 세계인의 입맛을 사로 잡은 버거의 대명사.',
        'A double layer of sear-sizzled 100% pure beef mingled with a special sauce on a sesame seed bun and topped with melty cheese, crisp lettuce, minced onions and tangy pickles.',
        6300, 'https://www.mcdonalds.co.kr/upload/product/pcList/1583727841393.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1583727841393.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1583727841393.png',
        'SELLABLE', 'HAMBURGER', '2019-05-30', '2019-05-30'
       ),
       ('맥크리스피™ 디럭스 버거', 'McCrispy™ Deluxe Burger',
        '100% 통닭다리살 겉바속촉 케이준 치킨 패티, 촉촉한 포테이토 브리오쉬 번, 스페셜 스모키 소스가 선사하는 놀랍도록 새로운 맛의 치킨 버거!',
        'Thick, whole-chicken thigh and drumstick meat, flavored with zesty Cajun seasoning, is deep-fried for the patty that is crispy on the outside, but juicy inside.',
        7600, 'https://www.mcdonalds.co.kr/upload/product/pcList/1653436835912.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1653436835912.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1653436835912.png',
        'SELLABLE', 'HAMBURGER', '2022-05-25', '2022-05-25'
       ),
       ('맥크리스피™ 클래식 버거', 'McCrispy™ Classic Burger',
        '100% 통닭다리살 겉바속촉 케이준 치킨 패티! 치킨 버거 본연의 맛에 충실한 클래식 버거',
        'Thick, whole-chicken thigh and drumstick meat, flavored with zesty Cajun seasoning, is deep-fried for the patty that is crispy on the outside, but juicy inside.',
        6700, 'https://www.mcdonalds.co.kr/upload/product/pcList/1653436573153.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1653436573153.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1653436573153.png',
        'SELLABLE', 'HAMBURGER', '2022-05-25', '2022-05-25'
       ),
       ('맥스파이시® 상하이 버거', 'McSpicy® Shanghai Burger',
        '매콤한 시즈닝을 입힌 100% 닭가슴 통살 위에 아삭아삭한 양상추와 신선한 토마토~겉은 바삭, 속은 부드러운 치킨 패티의 매콤함으로 입맛도 기분도 화끈하게!',
        'Tender, crispy chicken breast filet with spicy seasoning, topped with crisp lettuce and a fresh tomato slice.',
        6300, 'https://www.mcdonalds.co.kr/upload/product/pcList/1583728339451.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1583728339451.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1583728339451.png',
        'SELLABLE', 'HAMBURGER', '2019-05-30', '2019-05-30'
       ),
       ('1955® 버거', '1955® Burger',
        '113g 두툼한 순 쇠고기 패티, 특별한 1955 소스에 깊은 풍미의 그릴드 어니언까지! 맥도날드가 처음 생긴 1955년의 맛을 담은 영원한 오리지널 1955 버거',
        '113g of thick beef patty, deep flavor of grilled onion with special 1955 sauce! The taste of 1955 since McDonald’s began Eternal original 1955 burger',
        7200, 'https://www.mcdonalds.co.kr/upload/product/pcList/1599119588089.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1599119588089.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1599119588089.png',
        'SELLABLE', 'HAMBURGER', '2019-05-30', '2019-05-30'
       ),
       ('맥치킨® 모짜렐라', 'McChicken® Mozzarella',
        '든든한 맥치킨에 골든 모짜렐라 치즈 스틱 2개와 매콤한 아라비아따 소스를 더해 강렬하게 재탄생한 맥치킨 모짜렐라!',
        'Here comes our new and impressively tasty McChicken Mozzarella that features big McChicken, two Golden Mozzarella cheese sticks, and spicy Arabian-inspired sauce.',
        5000, 'https://www.mcdonalds.co.kr/upload/product/pcList/1583727633823.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1583727633823.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1583727633823.png',
        'SELLABLE', 'HAMBURGER', '2019-10-08', '2019-10-08'
       ),
       ('맥치킨®', 'McChicken®',
        '바삭한 치킨 패티, 고소한 화이트 마요 소스와 아삭한 양상추가 함께! 더 업그레이드된 맛으로 돌아온 맥치킨',
        'Taste the crispy chicken patty, savory white mayo sauce and crunch lettuce. McChicken is back with upgraded tastes!',
        4300, 'https://www.mcdonalds.co.kr/upload/product/pcList/1583730322955.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1583730322955.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1583730322955.png',
        'SELLABLE', 'HAMBURGER', '2019-10-08', '2019-10-08'
       ),
       ('더블 불고기 버거', 'Double Bulgogi Burger',
        '진한 불고기 소스의 패티가 두 장! 여기에 고소한 치즈와 마요네즈, 신선한 양상추를 곁들인 깊고 풍부한 맛.',
        'Fire up your taste buds with two patties marinated in Korean’s favorite Bulgogi sauce, melty cheese, creamy mayo and crisp lettuce.',
        5400, 'https://www.mcdonalds.co.kr/upload/product/pcList/1583727299888.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1583727299888.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1583727299888.png',
        'SELLABLE', 'HAMBURGER', '2019-05-30', '2019-05-30'
       ),
       ('에그 불고기 버거', 'Egg Bulgogi Burger',
        '불고기 버거에 국내산 1+등급 계란을 더해 맛의 정점을 찍다!',
        'Offers the highest quality with locally sourced 1+ grade egg added to Bulgogi Burger',
        5100, 'https://www.mcdonalds.co.kr/upload/product/pcList/1644572710018.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1644572710018.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1644572710018.png',
        'SELLABLE', 'HAMBURGER', '2019-05-30', '2019-05-30'
       ),
       ('자두 천도복숭아 칠러', 'Plum Peach Chiller',
        '상콤한 자두와 달콤한 천도복숭아의 만남! 자두 천도복숭아 칠러',
        'A meeting between a fresh plum and a sweet Peach! Plum Peach Chiller',
        3700, 'https://www.mcdonalds.co.kr/upload/product/pcList/1698851151138.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1698851151138.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1698851151138.png',
        'SELLABLE', 'DRINK', '2023-04-04', '2023-04-04'
       ),
       ('제주 한라봉 칠러', 'Jeju Hallabong Chiller',
        '제주 한라봉의 상큼새콤한 맛과 향을 즐겨보세요!',
        'Enjoy the refreshing taste and aroma of Jeju Hallabong!',
        3700, 'https://www.mcdonalds.co.kr/upload/product/pcList/1681287171454.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1681287171454.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1681287171454.png',
        'SELLABLE', 'DRINK', '2022-05-11', '2022-05-11'
       ),
       ('바닐라 라떼', 'Vanilla Latte',
        '바로 내린 100% 친환경 커피의 진한 맛과 향, 1A등급 우유, 그리고 천연 바닐라 향으로 달콤함까지!',
        'The strong taste and aroma of 100% eco-friendly coffee, 1A-grade milk and sweet natural vanilla flavor!',
        4500, 'https://www.mcdonalds.co.kr/upload/product/pcList/1677678747710.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1677678747710.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1677678747710.png',
        'SELLABLE', 'DRINK', '2021-11-02', '2021-11-02'
       ),
       ('아이스 바닐라 라떼', 'Iced Vanilla Latte',
        '바로 내린 100% 친환경 커피의 진한 맛과 향, 1A등급 우유, 그리고 천연 바닐라 향으로 달콤함까지!',
        'The strong taste and aroma of 100% eco-friendly coffee, 1A-grade milk and sweet natural vanilla flavor!',
        4500, 'https://www.mcdonalds.co.kr/upload/product/pcList/1654652258015.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1654652258015.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1654652258015.png',
        'SELLABLE', 'DRINK', '2021-11-02', '2021-11-02'
       ),
       ('카페라떼', 'Café Latte',
        '바로 내린 100% 친환경 커피가 신선한 우유를 만나 더 신선하고 부드럽게!',
        'Perfect harmony of fresh milk and premium espresso extracted from 100% Arabica beans!',
        4000, 'https://www.mcdonalds.co.kr/upload/product/pcList/1677680098077.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1677680098077.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1677680098077.png',
        'SELLABLE', 'DRINK', '2019-05-30', '2019-05-30'
       ),
       ('맥윙™', 'McWings™',
        '겉은 바삭, 속은 쫄깃! 치킨 땡길 땐 맥윙!',
        'Flavorful chicken wings with crispy on the outside and chewy on the inside!',
        4200, 'https://www.mcdonalds.co.kr/upload/product/pcList/1712028381366.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1712028381366.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1712028381366.png',
        'SELLABLE', 'DESSERT', '2019-09-24', '2019-09-24'
       ),
       ('맥윙™ 콤보', 'McWings™ combo',
        '겉은 바삭! 속은 쫄~깃한 치킨 윙! 그리고 시원한 탄산음료!',
        'Flavorful chicken wings with crispy on the outside and chewy on the inside!',
        7100, 'https://www.mcdonalds.co.kr/upload/product/pcList/1712028667694.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1712028667694.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1712028667694.png',
        'SELLABLE', 'DESSERT', '2021-04-23', '2021-04-23'
       ),
       ('코울슬로', 'Coleslaw',
        '양배추, 당근, 양파가 상큼하고 크리미한 마요 드레싱과 어우러져 아삭하게 씹히는 샐러드',
        'A crunchy salad with cabbage, carrots and onions combined <br>with a creamy mayonnaise dressing',
        2700, 'https://www.mcdonalds.co.kr/upload/product/pcList/1676460052498.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1676460052498.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1676460052498.png',
        'SELLABLE', 'DESSERT', '2022-05-24', '2022-05-24'
       ),
       ('베이컨 에그 맥그리들', 'Bacon Egg McGriddles',
        '메이플 시럽이 들어간 달콤한 핫케이크 번에 국내산 1+등급 계란, 고소한 베이컨으로 완성한 단짠의 맛! 단짠촉촉 맥그리들',
        'Sweet pancake buns with maple syrup, 1+ grade locally sourced egg and a savory bacon strip round up to a well-balanced taste of sweet and salty! Make your breakfast even more special with Bacon Egg McGriddles!',
        5100, 'https://www.mcdonalds.co.kr/upload/product/pcList/1680763640986.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1680763640986.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1680763640986.png',
        'SELLABLE', 'MC_MORNING', '2022-09-14', '2022-09-14'
       ),
       ('소시지 에그 맥그리들', 'Sausage Egg McGriddles',
        '메이플 시럽이 들어간 달콤한 핫케이크 번에 국내산 1+등급 계란, 촉촉한 소시지 패티로 완성한 단짠의 맛! 단짠촉촉 맥그리들',
        'Sweet pancake buns with maple syrup, 1+ grade locally sourced egg and a savory sausage patty round up to a well-balanced taste of sweet and salty! Make your breakfast even more special with Sausage Egg McGriddles!',
        5400, 'https://www.mcdonalds.co.kr/upload/product/pcList/1680763511600.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1680763511600.png',
        'https://www.mcdonalds.co.kr/upload/product/pcList/1680763511600.png',
        'SELLABLE', 'MC_MORNING', '2022-09-14', '2022-09-14'
       )
;


INSERT INTO product_option(option_type, add_price, product_id)
VALUES ('SINGLE', 0, 1), ('WITH_COKE', 900, 1), ('SET', 1600, 1),
       ('SINGLE', 0, 2), ('WITH_COKE', 900, 2), ('SET', 1600, 2),
       ('SINGLE', 0, 3), ('WITH_COKE', 900, 3), ('SET', 1600, 3),
       ('SINGLE', 0, 4), ('WITH_COKE', 900, 4), ('SET', 1600, 4),
       ('SINGLE', 0, 5), ('WITH_COKE', 900, 5), ('SET', 1600, 5),
       ('SINGLE', 0, 6), ('WITH_COKE', 900, 6), ('SET', 1600, 6),
       ('SINGLE', 0, 7), ('WITH_COKE', 900, 7), ('SET', 1600, 7),
       ('SINGLE', 0, 8), ('WITH_COKE', 900, 8), ('SET', 1600, 8),
       ('SINGLE', 0, 9), ('WITH_COKE', 900, 9), ('SET', 1600, 9),
       ('SINGLE', 0, 10), ('WITH_COKE', 900, 10), ('SET', 1600, 10),
       ('SINGLE', 0, 11), ('WITH_COKE', 900, 11), ('SET', 1600, 11),
       ('SINGLE', 0, 12), ('WITH_COKE', 900, 12), ('SET', 1600, 12),
       ('SINGLE', 0, 13), ('WITH_COKE', 900, 13), ('SET', 1600, 13),
       ('SINGLE', 0, 14), ('SINGLE', 0, 15), ('SINGLE', 0, 16), ('SINGLE', 0, 17), ('SINGLE', 0, 18),
       ('SINGLE', 0, 19), ('SINGLE', 0, 20), ('SINGLE', 0, 21), ('SINGLE', 0, 22), ('SINGLE', 0, 23);


SELECT * FROM PRODUCT;
