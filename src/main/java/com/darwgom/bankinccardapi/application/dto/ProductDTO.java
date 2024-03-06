package com.darwgom.bankinccardapi.application.dto;

import com.darwgom.bankinccardapi.domain.enums.ProductTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String productCode;
    private ProductTypeEnum type;
}
