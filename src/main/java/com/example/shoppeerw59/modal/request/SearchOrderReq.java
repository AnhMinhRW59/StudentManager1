package com.example.shoppeerw59.modal.request;

import com.example.shoppeerw59.modal.dto.BaseReq;
import com.example.shoppeerw59.modal.entity.Account;
import com.example.shoppeerw59.modal.entity.Product;
import com.example.shoppeerw59.modal.entity.StatusOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchOrderReq extends BaseReq {
    private Date orderDate;

    private Integer oderBy;

    private Integer productId;

    private StatusOrder status;
}
