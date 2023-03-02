package com.example.shoppeerw59.modal.request;

import com.example.shoppeerw59.modal.dto.BaseReq;
import com.example.shoppeerw59.modal.entity.ProductStatus;
import com.example.shoppeerw59.modal.entity.ProductType;
import com.example.shoppeerw59.modal.entity.ShippingUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchProductReq extends BaseReq {
    private String productName;

    private Set<ProductType> productTypes;

    private Set<ShippingUnit> shippingUnits;

    private Set<ProductStatus> productStatus;

    private Long minPrice;

    private Long maxPrice;
}
