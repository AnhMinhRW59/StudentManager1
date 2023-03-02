package com.example.shoppeerw59.Validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER}) //Khai báo vị trí sử dụng
@Retention(RetentionPolicy.RUNTIME) //Khai báo thời điểm sử dụng
@Documented
@Constraint(validatedBy = ProductIdExistsValidator.class) //Khai báo class xử lý Logic
public @interface ProductIdExists {
    //trường hợp message là bắt buộc, khai báo nội dung sẽ trả về khi field không hợp lệ
    String message() default "Sản phẩm không tồn tại";

    // 2 cái này là bắt buộc phải có để Hibernate Validator có thể hoạt động
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
