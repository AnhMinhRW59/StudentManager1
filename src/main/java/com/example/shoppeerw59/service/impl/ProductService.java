package com.example.shoppeerw59.service.impl;

import com.example.shoppeerw59.Exception.AppException;
import com.example.shoppeerw59.Exception.ErrorResponseBase;
import com.example.shoppeerw59.modal.dto.BaseReq;
import com.example.shoppeerw59.modal.entity.Account;
import com.example.shoppeerw59.modal.entity.Product;
import com.example.shoppeerw59.modal.request.CreateProductReq;
import com.example.shoppeerw59.modal.request.SearchProductReq;
import com.example.shoppeerw59.modal.request.UpdateProductReq;
import com.example.shoppeerw59.repository.IProductRepository;
import com.example.shoppeerw59.repository.Specification.ProductSpecification;
import com.example.shoppeerw59.service.IProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository repository;

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<Product> search(SearchProductReq req) {
        long minPrice = req.getMinPrice();
        long maxPrice = req.getMaxPrice();
        if (minPrice >= maxPrice){
            throw new AppException(ErrorResponseBase.MIN_MAXINVALID);
        }

        Specification<Product> productSpecification = ProductSpecification.buildCondition(req);
        PageRequest pageRequest = BaseReq.buildPageReq(req);

        return  repository.findAll(productSpecification, pageRequest);

    }


    @Override
    public Product getById(int id) {
        Optional<Product> optional = repository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }



    @Override
    public void create(CreateProductReq req) {
        Product product = new Product();
        BeanUtils.copyProperties(req, product);
        repository.save(product);

    }

    @Override
    public Product update(int id, UpdateProductReq updateProductReq) {
        Product productDb = getById(id);
        if(productDb != null){
            BeanUtils.copyProperties(updateProductReq, productDb);
            productDb.setId(id);
            repository.save(productDb);
        }
        return null;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);

    }
}
