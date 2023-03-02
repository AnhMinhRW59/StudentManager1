package com.example.shoppeerw59.modal.request;

import com.example.shoppeerw59.modal.entity.ProductStatus;
import com.example.shoppeerw59.modal.entity.ProductType;
import com.example.shoppeerw59.modal.entity.ShippingUnit;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateProductReq {

    private String name;

    @NotBlank(message = "{product.test}")
    private String  image; // ảnh đại diện sản phẩm

    private int price;

    private ProductStatus status;// tình trạng sản phẩm mới hay cũ


    private ShippingUnit shippingUnit;//đơn vị nhận vận chuyển


    private ProductType type;//loại sản phẩm bán

}
