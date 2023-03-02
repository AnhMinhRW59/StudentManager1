package com.example.shoppeerw59.modal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Entity
    @Table(name = "`Order`")
    public class Order {
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name = "order_date")
        private Date orderDate;// ngày order

        @ManyToOne
        @JoinColumn(name = "order_by")
        private Account oderBy;//người order

        @ManyToOne
        @JoinColumn(name = "product_id")
        private Product productId;

        @Column(name = "quantity")
        private int quantity;//số lượng sản phẩm đã order

        @Enumerated(EnumType.STRING)
        @Column(name = "status")
        private StatusOrder status;
    }

