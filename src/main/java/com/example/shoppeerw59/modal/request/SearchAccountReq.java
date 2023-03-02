package com.example.shoppeerw59.modal.request;

import com.example.shoppeerw59.modal.dto.BaseReq;
import com.example.shoppeerw59.modal.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchAccountReq extends BaseReq {

    private Role role;

    private String fullName;

    private String phoneNumber;

    private String email;

    private String facebook;

    private String information;
}
