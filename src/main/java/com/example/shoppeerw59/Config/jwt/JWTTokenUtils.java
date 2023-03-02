package com.example.shoppeerw59.Config.jwt;

import com.alibaba.fastjson.JSON;
import com.example.shoppeerw59.Exception.AppException;
import com.example.shoppeerw59.modal.dto.LoginDto;
import com.example.shoppeerw59.modal.entity.Role;
import com.example.shoppeerw59.modal.entity.Token;
import com.example.shoppeerw59.repository.Specification.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Slf4j
@Component
public class JWTTokenUtils {
    private static final long EXPIRATION_TIME = 864000000; //10 days
    private static final String SECRET = "1234567"; //CHữ ký bí mật
    private static final String PREFIX_TOKEN = "Bearer"; //Ký tự đầu của token
    private static final String AUTHORIZATION = "Authorization"; //Key của token trên header

    @Autowired
    private TokenRepository tokenRepository;

    // Hàm này dùng để tạo ra token
    public  String createAccessToken(LoginDto loginDto){
        //Tạo giá trị thời hạn Token (bằng thời gian hiện tại + 10 ngày hoặc tùy theo giá trị mình set ở trên
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        String token = Jwts.builder()
                .setId(String.valueOf(loginDto.getId())) //set giá trị id
                .setSubject(loginDto.getUsername()) //Set giá trị subject
                .setIssuedAt(new Date())
                .setIssuer("VTI")
                .setExpiration(expirationDate) //Set thời hạn của token
        .signWith(SignatureAlgorithm.HS256, SECRET) // Khai báo phương thức mã hóa token và chữ kí bí mật
        .claim("authorities", loginDto.getRole().name()) //thêm trường authorities để lưu giá trị phân quyền
        .claim("user-Agent", loginDto.getUserAgent()).compact(); // thêm trường user-Agent để lưu thông tin trình duyệt đang dùng

        //Tạo đối tượng Token và lưu và database
        Token tokenEntity = new Token();
        tokenEntity.setToken(token);
        tokenEntity.setExpiration(expirationDate);
        tokenEntity.setUserAgent(loginDto.getUserAgent());
        tokenRepository.save(tokenEntity);
        return token;
    }

    public boolean checkToken(String token, HttpServletResponse response, HttpServletRequest httpServletRequest) {
        try {
            if (StringUtils.isBlank(token) || !token.startsWith(PREFIX_TOKEN)) { // token bị trống -> lỗi
                responseJson(response, new AppException("Token ko hợp lệ", 401, httpServletRequest.getRequestURI()));
                return false;
            }
            token = token.replace(PREFIX_TOKEN, "").trim();

            Token tokenEntity = tokenRepository.findByToken(token);
            if (tokenEntity == null) { // Ko có token trên hệ thống
                responseJson(response, new AppException("Token ko tồn tại",401, httpServletRequest.getRequestURI()));
                return false;
            }

//            String ip = httpServletRequest.getHeader("User-Agent");
//            if (!StringUtils.equals(ip, tokenEntity.getUserAgent())) { // Token sử dụng ở một nơi khác
//                responseJson(response, new AppException("Token đc dùng ở chỗ khác",401, httpServletRequest.getRequestURI()));
//                return false;
//            }

            if (tokenEntity.getExpiration().after(new Date(System.currentTimeMillis() + EXPIRATION_TIME))) { // Token hết hạn
                responseJson(response, new AppException("Token hết hiệu lực",401, httpServletRequest.getRequestURI()));
                return false;
            }
        } catch (Exception e) {
            responseJson(response, new AppException(e.getMessage(),401, httpServletRequest.getRequestURI()));
            return false;
        }
        return true;
    }

    // Hàm này dùng để response dữ liệu khi gặp lỗi
    private void responseJson(HttpServletResponse response, AppException appException){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(appException.getCode());
        try {
            response.getWriter().print(JSON.toJSONString(appException));
        } catch (IOException e) {
            log.debug(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Hàm này dùng để giải mã hoá token
    public LoginDto parseAccessToken(String token) {

        LoginDto loginDto = new LoginDto();
        if (!token.isEmpty()) {
            try {
                token = token.replace(PREFIX_TOKEN, "").trim();
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token).getBody();
                // Lấy ra các thông tin
                String user = claims.getSubject();
                Role role = Role.valueOf(claims.get("authorities").toString());
                String userAgent = claims.get("user-Agent").toString();

                // Gán các thông tin vào đối tượng LoginDto, có thể sử dụng constructor
                loginDto.setUsername(user);
                loginDto.setRole(role);
                loginDto.setUserAgent(userAgent);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return loginDto;
    }
}
