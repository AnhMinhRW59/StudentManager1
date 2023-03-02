package com.example.shoppeerw59.modal.request;

import com.example.shoppeerw59.modal.entity.Role;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateAccountReq {
    private Role role;


    private String password;

    private Date dateOfBirth;


    private String address;

    private String fullName;

    private String phoneNumber;

    private String email;

    private String facebook;

    private String information;
}

