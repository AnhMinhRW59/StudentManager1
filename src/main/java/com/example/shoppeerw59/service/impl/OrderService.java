package com.example.shoppeerw59.service.impl;

import com.example.shoppeerw59.modal.dto.BaseReq;
import com.example.shoppeerw59.modal.entity.Account;
import com.example.shoppeerw59.modal.entity.Order;
import com.example.shoppeerw59.modal.entity.Product;
import com.example.shoppeerw59.modal.entity.StatusOrder;
import com.example.shoppeerw59.modal.request.CreateOrderReq;
import com.example.shoppeerw59.modal.request.SearchOrderReq;
import com.example.shoppeerw59.modal.request.UpdateOrderReq;
import com.example.shoppeerw59.repository.IOrderRepository;
import com.example.shoppeerw59.repository.Specification.OrderSpecification;
import com.example.shoppeerw59.repository.Specification.ProductSpecification;
import com.example.shoppeerw59.service.IOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository repository;

    @Autowired
    AccountService accountService;

    @Autowired
    ProductService productService;

    @Override
    public List<Order> getAll() {
        return repository.findAll();
    }

    @Override
    public Order getById(int id) {
        Optional<Order> optional = repository.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public void create(CreateOrderReq req) {
        Order order = new Order();
        BeanUtils.copyProperties(req, order);
        //Get 2 đối tượng
        Account account = accountService.getById(req.getAccountId());
        Product product = productService.getById(req.getProductId());
        if(account != null && product != null) {
            // Setup creatBy và product not null
            order.setOderBy(account);
            order.setProductId(product);
            order.setOrderDate(new Date());
            order.setStatus(StatusOrder.PENDING);
            repository.save(order);
        }
    }

    @Override
    public Order update(int id, UpdateOrderReq updateOrderReq) {
        Order orderDb = getById(id);
        if(orderDb != null){
            BeanUtils.copyProperties(updateOrderReq, orderDb);
            orderDb.setId(id);
            repository.save(orderDb);
        }
        return null;
    }

    @Override
    public void delete(int id) {
         repository.deleteById(id);
    }

    @Override
    public Page<Order> search(SearchOrderReq req) {
        Specification<Order> orderSpecification = OrderSpecification.buildCondition(req);
        PageRequest pageRequest = BaseReq.buildPageReq(req);

        return repository.findAll(orderSpecification, pageRequest);
    }
}
