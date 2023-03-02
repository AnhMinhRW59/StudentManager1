package com.example.shoppeerw59.repository.Specification;

import com.example.shoppeerw59.modal.entity.Product;
import com.example.shoppeerw59.modal.entity.ProductStatus;
import com.example.shoppeerw59.modal.entity.ProductType;
import com.example.shoppeerw59.modal.entity.ShippingUnit;
import com.example.shoppeerw59.modal.request.SearchProductReq;
import org.springframework.data.jpa.domain.Specification;

import java.util.Set;

public class ProductSpecification {
    public static Specification<Product> buildCondition(SearchProductReq req){
        return Specification.where(searchByProductName(req.getProductName()))
                .and(searchByProductType(req.getProductTypes()))
                .and(searchByShippingUnit(req.getShippingUnits()))
                .and(searchByStatus(req.getProductStatus()))
                .and(searchByMin(req.getMinPrice()))
                .and(searchByMax(req.getMaxPrice()));
    }

    private static Specification<Product> searchByProductName(String productName) {
        if (productName != null) {
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("name"), "%" + productName + "%");
            };
        } else {
            return null;
        }
    }

    private static Specification<Product> searchByProductType(Set<ProductType> productTypes){
        if (productTypes != null && productTypes.size()>0){
            return (root, query, criteriaBuilder) -> {
                return root.get("type").in(productTypes);
            };
        }
        return null;
    }

    private static Specification<Product> searchByShippingUnit(Set<ShippingUnit> shippingUnits){
        if (shippingUnits != null && shippingUnits.size()>0){
            return (root, query, criteriaBuilder) -> {
                return root.get("shippingUnit").in(shippingUnits);
            };
        }
        return null;
    }

    private static Specification<Product> searchByStatus(Set<ProductStatus> productStatus){
        if (productStatus != null && productStatus.size()>0){
            return (root, query, criteriaBuilder) -> {
                return root.get("status").in(productStatus);
            };
        }
        return null;
    }

    private static Specification<Product> searchByMin(Long minPrice){
        if (minPrice != null){
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
            };
        }
        return null;
    }

    private static Specification<Product> searchByMax(Long maxPrice){
        if (maxPrice != null){
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
            };
        }
        return null;
    }

}
