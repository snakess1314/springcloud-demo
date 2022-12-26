package com.wzq.springcloudcardprovider.annatation;

import com.wzq.springcloudcardprovider.config.CheckValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CheckValidator.class})//指向实现验证的类
public @interface Check {
    CheckValidatorEnum type() default CheckValidatorEnum.Null;

    long min() default 1;

    long max() default 1;

    String message() default "参数异常";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

