package com.study.kioskback.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product_option")
@Entity
public class ProductOption {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "option_type")
    private ProductOptionType optionType;

    @Column(name = "add_price")
    private Integer addPrice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductOption(ProductOptionType optionType, Integer addPrice) {
        this.optionType = optionType;
        this.addPrice = addPrice;
    }

    //== 연관관계 편의메소드==//
    public void addProduct(Product product) {
        this.product = product;
    }
}
