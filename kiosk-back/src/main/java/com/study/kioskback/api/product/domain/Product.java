package com.study.kioskback.api.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product")
@Entity
public class Product {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id @Column(name = "product_id")
    private Integer id;

    @Column(name = "product_name_kr")
    private String koreanName;

    @Column(name = "product_name_en")
    private String englishName;

    @Column(name = "product_contents_kr")
    private String koreanContent;

    @Column(name = "product_contents_en")
    private String englishContent;

    @Column(name = "product_price")
    private Integer price;

    @Embedded
    ProductUploadFile productUploadFile;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_status")
    private ProductStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_type")
    private ProductType type;

    @Column(name = "product_created_date")
    private LocalDateTime createdAt;

    @Column(name = "product_updated_date")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductOption> options = new ArrayList<>();

    @Builder
    public Product(String koreanName, String englishName, String koreanContent, String englishContent,
                   Integer price, ProductUploadFile productUploadFile,
                   ProductStatus status, ProductType type,
                   LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.koreanName = koreanName;
        this.englishName = englishName;
        this.koreanContent = koreanContent;
        this.englishContent = englishContent;
        this.price = price;
        this.productUploadFile = productUploadFile;
        this.status = status;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void changeUploadFile(ProductUploadFile productUploadFile) {
        this.productUploadFile = productUploadFile;
    }

    //== 연관관계 편의메소드==//
    public void addOptions(List<ProductOption> productOptions) {
        this.options.addAll(productOptions);
        for (ProductOption option : productOptions) {
            option.addProduct(this);
        }
    }
}
