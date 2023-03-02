package com.example.shoppeerw59.modal.dto;

import com.example.shoppeerw59.modal.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    private int id;
    private String username;
    private Role role;
    private String fullName;
    private String userAgent; //Thông tin trình duyệt đang sử dụng
    private String token;
}
