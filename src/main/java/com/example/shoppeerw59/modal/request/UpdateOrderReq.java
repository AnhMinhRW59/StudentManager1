package com.example.shoppeerw59.modal.request;

import com.example.shoppeerw59.modal.entity.StatusOrder;
import lombok.Data;

@Data
public class UpdateOrderReq {
    private int quantity;//số lượng sản phẩm đã order

    private StatusOrder status;
}
