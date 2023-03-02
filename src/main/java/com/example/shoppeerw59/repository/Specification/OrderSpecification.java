package com.example.shoppeerw59.repository.Specification;

import com.example.shoppeerw59.modal.entity.Account;
import com.example.shoppeerw59.modal.entity.Order;
import com.example.shoppeerw59.modal.entity.Product;
import com.example.shoppeerw59.modal.entity.StatusOrder;
import com.example.shoppeerw59.modal.request.SearchOrderReq;
import com.example.shoppeerw59.modal.request.SearchProductReq;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import java.util.Date;

public class OrderSpecification {
    public static Specification<Order> buildCondition(SearchOrderReq req){
        return Specification.where(searchByOrderDate(req.getOrderDate()))
                .and(searchByOrderBy(req.getOderBy()))
                .and(searchByStatus(req.getStatus()))
                .and(searchByProductId(req.getProductId()));
    }


    private static Specification<Order> searchByOrderDate(Date date){
        if (date != null){
            return ((root, query, criteriaBuilder) -> {
                return criteriaBuilder.equal(root.get("orderDate"), date);
            });
        } else {
            return null;
        }
    }

    private static Specification<Order> searchByOrderBy(Integer orderBy){
        if (orderBy > 0){
            return ((root, query, criteriaBuilder) -> {
                Join<Order, Account> joiner = root.join("orderBy");
                return criteriaBuilder.equal(joiner.get("id"), orderBy);
            });
        } else {
            return null;
        }
    }

    private static Specification<Order> searchByProductId(Integer productId){
        if (productId > 0){
            return ((root, query, criteriaBuilder) -> {

                Join<Order, Product> productJoin = root.join("productId");
                return criteriaBuilder.equal(productJoin.get("id"), productJoin);
            });
        }else {
            return null;
        }
    }

    private static Specification<Order> searchByStatus(StatusOrder status){
        if (status != null){
            return ((root, query, criteriaBuilder) -> {
                return criteriaBuilder.equal(root.get("status"), status);
            });
        }else {
            return null;
        }
    }
}
