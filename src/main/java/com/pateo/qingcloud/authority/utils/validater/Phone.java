package com.pateo.qingcloud.authority.utils.validater;

/**
 * Created by sean on 2017/11/4.
 */
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Created by sean on 2017/11/4.
 */
@Constraint(validatedBy = PhoneValidator.class) //具体的实现
@Target( { java.lang.annotation.ElementType.METHOD,
        java.lang.annotation.ElementType.FIELD })
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
public @interface Phone {
    String message() default "{is not a phone}"; //提示信息,可以写死,可以填写国际化的key

    int length() default 5;

    //下面这两个属性必须添加
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}