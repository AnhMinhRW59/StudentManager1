package com.example.shoppeerw59.modal.request;


import com.example.shoppeerw59.modal.entity.Role;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class CreateAccountReq {

    @NotBlank(message = "Điền cái tên vào")
    private String username;


    private Role role;

    @NotBlank(message = "Điền cái pass vào")
    private String password;

    private Date dateOfBirth;


    private String address;

    private String fullName;

    private String phoneNumber;

    private String email;

    private String facebook;

    private String information;
}
