package com.study.kioskback.api.product.controller;

import com.study.kioskback.api.product.dto.ProductPreviewDto;
import com.study.kioskback.api.product.dto.ProductRequestDto;
import com.study.kioskback.api.product.dto.ProductSearch;
import com.study.kioskback.api.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@RestController
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductPreviewDto> findById(@PathVariable Integer productId) {
        ProductPreviewDto responseDto = productService.findById(productId);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductPreviewDto>> findAllBy(ProductSearch productSearch) {
        List<ProductPreviewDto> products = productService.findAllBy(productSearch);
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Integer> saveProduct(ProductRequestDto requestDto) throws IOException {
        Integer savedId = productService.saveProduct(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedId);
    }

}
