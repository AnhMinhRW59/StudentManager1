package com.example.shoppeerw59.modal.request;

import com.example.shoppeerw59.Validate.ProductIdExists;
import com.example.shoppeerw59.modal.entity.Account;
import com.example.shoppeerw59.modal.entity.Product;
import com.example.shoppeerw59.modal.entity.StatusOrder;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
public class CreateOrderReq {

    private Date orderDate;// ngày order

    private int accountId;//người order

    @ProductIdExists
    private int productId;

    private int quantity;//số lượng sản phẩm đã order

    private StatusOrder status;
}
