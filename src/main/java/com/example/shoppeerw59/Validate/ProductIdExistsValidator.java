package com.example.shoppeerw59.Validate;


import com.example.shoppeerw59.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//GHI CHÚ: ConstraintValidator <Annotation đã tạo, kiểu dữ liệu muốn kiểm tra>
public class ProductIdExistsValidator implements ConstraintValidator<ProductIdExists, Integer> {
    @Autowired
    IProductRepository repository;


    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        // Logic kiểm tra dữ liệu
        return repository.existsById(integer);
    }
}
