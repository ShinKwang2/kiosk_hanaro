package com.study.kioskback.product.service;

import com.study.kioskback.product.domain.Product;
import com.study.kioskback.product.domain.ProductStatus;
import com.study.kioskback.product.domain.ProductType;
import com.study.kioskback.product.domain.ProductUploadFile;
import com.study.kioskback.product.dto.ProductPreviewDto;
import com.study.kioskback.product.dto.ProductRequestDto;
import com.study.kioskback.product.dto.ProductSearch;
import com.study.kioskback.product.repository.ProductRepository;
import com.study.kioskback.util.FileStore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private FileStore fileStore;

    private final String KOREAN_NAME = "더블 빅맥";
    private final String ENGLISH_NAME = "Double Big Mac";
    private final Integer PRICE = 5000;

    @DisplayName("제품 타입으로 조회")
    @Test
    void findAllProductByCondition() throws Exception {
        //given
        String uuid = UUID.randomUUID().toString();
        Product product = Product.builder().koreanName(KOREAN_NAME)
                .englishName(ENGLISH_NAME)
                .productUploadFile(new ProductUploadFile("test.png", uuid, uuid))
                .price(PRICE)
                .type(ProductType.HAMBURGER)
                .build();
        productRepository.save(product);
        ProductSearch productSearch = ProductSearch.builder().page(1).size(10).type("hamburger").build();

        //when
        List<ProductPreviewDto> hamburgers = productService.findAllBy(productSearch);

        //then
        assertThat(hamburgers).hasSize(1)
                .extracting("koreanName", "englishName", "imageUrlForList", "price")
                .containsExactlyInAnyOrder(
                        tuple(KOREAN_NAME, ENGLISH_NAME, uuid, PRICE)
                );
    }

    @DisplayName("제품 저장")
    @Test
    void saveProduct() throws Exception {
        //given
        byte[] fileContent = "test file content".getBytes();
        MockMultipartFile multipartFile = new MockMultipartFile("test", "test.png", MediaType.TEXT_PLAIN_VALUE, fileContent);
        ProductRequestDto requestDto = createBigMac(multipartFile);

        //when
        Integer savedId = productService.saveProduct(requestDto);

        //then
        Product product = productRepository.findById(savedId).get();
        assertThat(product.getKoreanName()).isEqualTo(KOREAN_NAME);
        assertThat(product.getEnglishName()).isEqualTo(ENGLISH_NAME);
        assertThat(product.getKoreanContent()).isEqualTo("한 입에 가득 차는 100% 순 쇠고기 패티 4장의 <br>\\r\\n진한 육즙과 풍미가 입안 가득!\\r\\n<br><br>\\r\\n*판매 시간: 10:30AM~4AM");
        assertThat(product.getEnglishContent()).isEqualTo("With four 100% real beef patties, enjoy the deep taste <br>and flavor of the juicy beef patties that fill your mouth!\\r\\n<br><br>\\r\\n*Available from 10:30AM~4AM");
        assertThat(product.getPrice()).isEqualTo(PRICE);
        assertThat(product.getStatus()).isEqualTo(ProductStatus.SELLABLE);
        assertThat(product.getType()).isEqualTo(ProductType.HAMBURGER);
        assertThat(product.getProductUploadFile().getUploadFileName()).isEqualTo("test.png");

        fileStore.deleteFile(product.getProductUploadFile().getStoreFileName());
    }

    private ProductRequestDto createBigMac(MockMultipartFile multipartFile) {
        ProductRequestDto requestDto = ProductRequestDto.builder()
                .koreanName(KOREAN_NAME)
                .englishName(ENGLISH_NAME)
                .koreanContent("한 입에 가득 차는 100% 순 쇠고기 패티 4장의 <br>\\r\\n진한 육즙과 풍미가 입안 가득!\\r\\n<br><br>\\r\\n*판매 시간: 10:30AM~4AM")
                .englishContent("With four 100% real beef patties, enjoy the deep taste <br>and flavor of the juicy beef patties that fill your mouth!\\r\\n<br><br>\\r\\n*Available from 10:30AM~4AM")
                .price(PRICE)
                .productStatus("sellable")
                .productType("hamburger")
                .imageFile(multipartFile)
                .build();
        return requestDto;
    }
}