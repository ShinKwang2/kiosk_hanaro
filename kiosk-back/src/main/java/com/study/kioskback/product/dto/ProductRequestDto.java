package com.study.kioskback.product.dto;

import com.study.kioskback.product.domain.Product;
import com.study.kioskback.product.domain.ProductStatus;
import com.study.kioskback.product.domain.ProductType;
import com.study.kioskback.product.domain.ProductUploadFile;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Builder
@Data
public class ProductRequestDto {

    @NotEmpty
    private String koreanName;

    @NotEmpty
    private String englishName;

    @NotEmpty
    private String koreanContent;

    @NotEmpty
    private String englishContent;

    @Min(0)
    private Integer price;

    @NotEmpty
    private String productStatus;

    @NotEmpty
    private String productType;

    @NotEmpty
    private MultipartFile imageFile;

    public Product toEntity(ProductUploadFile productUploadFile) {
        LocalDateTime now = LocalDateTime.now();
        return Product.builder()
                .koreanName(koreanName)
                .englishName(englishName)
                .koreanContent(koreanContent)
                .englishContent(englishContent)
                .price(price)
                .productUploadFile(productUploadFile)
                .status(ProductStatus.convertToStatus(productStatus))
                .type(ProductType.convertToType(productType))
                .createdAt(now)
                .updatedAt(now)
                .build();
    }
}
