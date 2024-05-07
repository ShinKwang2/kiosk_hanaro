package com.study.kioskback.product.controller;

import com.study.kioskback.product.dto.ProductPreviewDto;
import com.study.kioskback.product.dto.ProductRequestDto;
import com.study.kioskback.product.dto.ProductSearch;
import com.study.kioskback.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
