package com.wzq.springcloudcardprovider.config;

import com.wzq.springcloudcardprovider.annatation.Check;
import com.wzq.springcloudcardprovider.annatation.CheckValidatorEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class CheckValidator implements ConstraintValidator<Check, Object> {
    private Check check;
    private CheckValidatorEnum checkValidatorEnum;

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (checkValidatorEnum.getCode() == CheckValidatorEnum.Null.getCode()) {
            //非空校验
            if (o != null && !o.toString().equals("")) {
                return true;
            }
        } else if (checkValidatorEnum.getCode() == CheckValidatorEnum.AGE.getCode()) {
            //年龄校验
            if (o.toString() == null) {
                return true;
            }
            long min = this.check.min();
            long max = this.check.max();
            Integer age = Integer.parseInt(o.toString());
            if (age >= min && age <= max) {
                return true;
            }
        } else if (checkValidatorEnum.getCode() == CheckValidatorEnum.Phone.getCode()) {
            if (o == null) {
                return true;
            }
            //手机号码校验

            return true;

        }
        return false;
    }

    @Override
    public void initialize(Check constraintAnnotation) {
        this.check = constraintAnnotation;
        this.checkValidatorEnum = constraintAnnotation.type();
    }
}
