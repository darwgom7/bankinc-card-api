package com.darwgom.bankinccardapi.infrastucture.controllers;

import com.darwgom.bankinccardapi.application.dto.GenericOutputDTO;
import com.darwgom.bankinccardapi.application.dto.ProductInputDTO;
import com.darwgom.bankinccardapi.application.dto.ProductDTO;
import com.darwgom.bankinccardapi.application.usecases.ProductUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductUseCase productUseCase;

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductInputDTO productInputDTO) {
        ProductDTO productDTO = productUseCase.createProduct(productInputDTO);
        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> productsDTO = productUseCase.getAllProducts();
        return new ResponseEntity<>(productsDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<GenericOutputDTO> deleteProduct(@PathVariable Long productId) {
        GenericOutputDTO deletedProduct = productUseCase.deleteProduct(productId);
        return new ResponseEntity<>(deletedProduct, HttpStatus.OK);
    }
}
