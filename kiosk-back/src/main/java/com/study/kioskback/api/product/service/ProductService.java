package com.study.kioskback.api.product.service;

import com.study.kioskback.api.product.domain.*;
import com.study.kioskback.api.product.dto.ProductRequestDto;
import com.study.kioskback.api.product.dto.ProductSearch;
import com.study.kioskback.api.product.repository.ProductOptionRepository;
import com.study.kioskback.api.product.repository.ProductRepository;
import com.study.kioskback.error.exception.product.NotFoundProduct;
import com.study.kioskback.api.product.dto.ProductPreviewDto;
import com.study.kioskback.util.FileStore;
import com.study.kioskback.util.UploadFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductOptionRepository productOptionRepository;

    private final FileStore fileStore;

    //TODO
    @Transactional
    public Integer saveProduct(ProductRequestDto requestDto) throws IOException {
        MultipartFile imageFile = requestDto.getImageFile();
        log.info("imageFile={}", imageFile.getName());
        UploadFile uploadFile = fileStore.storeFile(imageFile);
        ProductUploadFile productUploadFile = ProductUploadFile.from(uploadFile);

        Product product = requestDto.toEntity(productUploadFile);
        log.info("product.getUploadFile={}", product.getProductUploadFile().getUploadFileName());

        List<ProductOption> options = addOptions(product);
        product.addOptions(options);

        Product savedProduct = productRepository.save(product);

        return savedProduct.getId();
    }

    private List<ProductOption> addOptions(Product savedProduct) {
        List<ProductOption> options = new ArrayList<>();
        if (savedProduct.getType().equals(ProductType.HAMBURGER)) {
            options.add(new ProductOption(ProductOptionType.SINGLE, 0));
            options.add(new ProductOption(ProductOptionType.WITH_COKE, 900));
            options.add(new ProductOption(ProductOptionType.SET, 1600));
            return options;
        }
        options.add(new ProductOption(ProductOptionType.SINGLE, 0));
        return options;
    }

    //TODO
    @Transactional
    public Integer updateProduct() {
        return null;
    }

    @Transactional
    public Integer deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
        return productId;
    }

    public ProductPreviewDto findById(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(NotFoundProduct::new);
        return ProductPreviewDto.of(product);
    }

    //TODO
    public List<ProductPreviewDto> findAllBy(ProductSearch productSearch) {
        return productRepository.findAllBy(productSearch).stream()
                .map(ProductPreviewDto::of)
                .collect(Collectors.toList());
    }
}
