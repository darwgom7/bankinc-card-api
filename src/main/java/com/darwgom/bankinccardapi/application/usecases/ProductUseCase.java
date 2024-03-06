package com.darwgom.bankinccardapi.application.usecases;

import com.darwgom.bankinccardapi.application.dto.GenericOutputDTO;
import com.darwgom.bankinccardapi.application.dto.ProductInputDTO;
import com.darwgom.bankinccardapi.application.dto.ProductDTO;

import java.util.List;

public interface ProductUseCase {
    ProductDTO createProduct(ProductInputDTO productInputDTO);
    List<ProductDTO> getAllProducts();
    GenericOutputDTO deleteProduct(Long productId);
}
