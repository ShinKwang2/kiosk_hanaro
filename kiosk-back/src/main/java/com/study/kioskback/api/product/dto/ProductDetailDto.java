package com.study.kioskback.api.product.dto;

import com.study.kioskback.api.product.domain.Product;
import com.study.kioskback.api.product.domain.ProductOption;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProductDetailDto {

    private Integer id;
    private String koreanName;
    private String englishName;
    private String koreanContent;
    private String englishContent;
    private String imageUrlForList;
    private Integer price;
    private List<ProductOptionDto> options;

    @Builder
    private ProductDetailDto(Integer id, String koreanName, String englishName, String koreanContent, String englishContent,
                            String imageUrlForList, Integer price, List<ProductOptionDto> options) {
        this.id = id;
        this.koreanName = koreanName;
        this.englishName = englishName;
        this.koreanContent = koreanContent;
        this.englishContent = englishContent;
        this.imageUrlForList = imageUrlForList;
        this.price = price;
        this.options = options;
    }

    public static ProductDetailDto of(Product product) {
        return ProductDetailDto.builder()
                .id(product.getId())
                .koreanName(product.getKoreanName())
                .englishName(product.getEnglishName())
                .koreanContent(product.getKoreanContent())
                .englishContent(product.getEnglishContent())
                .imageUrlForList(product.getProductUploadFile().getImageUrlForList())
                .price(product.getPrice())
                .options(product.getOptions().stream().map(ProductOptionDto::of).collect(Collectors.toList()))
                .build();
    }

    @Getter
    static class ProductOptionDto {
        private Integer id;
        private String optionName;
        private Integer addPrice;

        @Builder
        private ProductOptionDto(Integer id, String optionName, Integer addPrice) {
            this.id = id;
            this.optionName = optionName;
            this.addPrice = addPrice;
        }

        public static ProductOptionDto of(ProductOption option) {
            return ProductOptionDto.builder()
                    .id(option.getId())
                    .optionName(option.getOptionType().getKoreanName())
                    .addPrice(option.getAddPrice())
                    .build();
        }
    }
}
