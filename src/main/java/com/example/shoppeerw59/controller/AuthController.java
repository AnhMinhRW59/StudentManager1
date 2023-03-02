package com.example.shoppeerw59.controller;

import com.example.shoppeerw59.Config.jwt.JWTTokenUtils;
import com.example.shoppeerw59.Exception.AppException;
import com.example.shoppeerw59.Exception.ErrorResponseBase;
import com.example.shoppeerw59.modal.dto.LoginDto;
import com.example.shoppeerw59.modal.entity.Account;
import com.example.shoppeerw59.modal.request.LoginReq;
import com.example.shoppeerw59.repository.IAccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin("*")
@Validated
public class AuthController {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private JWTTokenUtils jwtTokenUtils;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    HttpServletRequest httpServletRequest;

    @PostMapping("/login")
    public LoginDto login(Principal principal){ //đối tượng Principal có chứa thông tin username
        // Khi qua bước authen sẽ tạo ra đối tượng Principal, tại controller sẽ sử dụng lại giá trị của dữ liệu này
        String userName = principal.getName();
        Optional<Account> accountOptional = accountRepository.getByUsername(userName);
        if (accountOptional.isEmpty()){
            throw new AppException(ErrorResponseBase.LOGIN_FAILS);
        }
        LoginDto loginDto = new LoginDto();
        BeanUtils.copyProperties(accountOptional.get(), loginDto);
        return loginDto;
    }

    @PostMapping("/login-v2")
    public LoginDto loginJWT(@RequestBody LoginReq req){
        // TÌm kiếm xem User có tồn tại trong hệ thống hay không
        Optional<Account> accountOptional = accountRepository.getByUsername(req.getUsername());
        if (accountOptional.isEmpty()){
            throw new AppException(ErrorResponseBase.LOGIN_FAILS_USERNAME);
        }
        // Kiểm tra xem pasword người dùng truyền vào có đúng hay không
        if (!encoder.matches(req.getPassword(), accountOptional.get().getPassword())){
            throw new AppException(ErrorResponseBase.LOGIN_FAILS_PASSWORD);
        }

        // Tạo đối tượng LoginDto để trả về
        LoginDto loginDto = new LoginDto();
        BeanUtils.copyProperties(accountOptional.get(), loginDto);
        loginDto.setUserAgent(httpServletRequest.getHeader("User-Agent")); //Lấy thông tin trình duyệt đang sử dụng
        String token = jwtTokenUtils.createAccessToken(loginDto); //Tạo token
        loginDto.setToken(token); //Set giá trị token vào loginDto để trả về cho người dùng sử dụng
        return loginDto;
    }
}
