package com.example.shoppeerw59.repository.Specification;

import com.example.shoppeerw59.modal.entity.Account;
import com.example.shoppeerw59.modal.entity.Product;
import com.example.shoppeerw59.modal.entity.Role;
import com.example.shoppeerw59.modal.request.SearchAccountReq;
import com.example.shoppeerw59.modal.request.SearchProductReq;
import org.springframework.data.jpa.domain.Specification;

public class AccountSpecification {

    public static Specification<Account> buildCondition(SearchAccountReq req) {
        return Specification.where(searchByFullname(req.getFullName()))
                .and(searchByRole(req.getRole()))
                .and(searchByPhoneNumber(req.getPhoneNumber()))
                .and(searchByEmail(req.getEmail()))
                .and(searchByFacebook(req.getFacebook()))
                .and(searchByInformation(req.getInformation()));
    }

    private static Specification<Account> searchByFullname(String fullName) {
        if (fullName != null) {
            return ((root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("fullName"), "%" + fullName + "%");
            });
        } else {
            return null;
        }
    }

    private static Specification<Account> searchByRole(Role role) {
        if (role != null) {
            return ((root, query, criteriaBuilder) -> {
                return criteriaBuilder.equal(root.get("role"), role);
            });
        } else {
            return null;
        }
    }

    private static Specification<Account> searchByPhoneNumber(String phoneNumber){
        if (phoneNumber != null){
            return ((root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("phoneNumber"), "%" + phoneNumber + "%");
            });
        } else {
            return null;
        }
    }

    private static Specification<Account> searchByEmail(String email){
        if (email != null) {
            return ((root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("email"), "%" + email + "%");
            });
        } else {
            return null;
        }
    }

    private static Specification<Account> searchByFacebook(String facebook) {
        if (facebook != null) {
            return ((root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("facebook"), "%" + facebook + "%");
            });
        } else {
            return null;
        }
    }

    private static Specification<Account> searchByInformation(String information){
        if (information != null) {
            return ((root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("information"), "%" + information + "%");
            });
        } else {
            return null;
        }
    }

}

