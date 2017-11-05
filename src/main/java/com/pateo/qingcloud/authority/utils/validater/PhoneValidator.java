package com.pateo.qingcloud.authority.utils.validater;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by sean on 2017/11/4.
 */
public class PhoneValidator implements ConstraintValidator<Phone,String> {
    private int len;
    @Override
    public void initialize(Phone phone) {
        this.len = phone.length();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(AccountValidatorUtil.isMobile(s)){
            return true;
        }else{
            constraintValidatorContext.disableDefaultConstraintViolation();//禁用默认的message的值
            //重新添加错误提示语句
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("手机号码不正确").addConstraintViolation();
        }
        return false;
    }
}
