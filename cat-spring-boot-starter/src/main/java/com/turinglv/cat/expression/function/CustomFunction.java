package com.turinglv.cat.expression.function;

import com.turinglv.cat.commons.lang.Strings;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义函数
 *
 * @author tlv
 * @since 1.0.0
 */
@Component
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomFunction {

	String value() default Strings.EMPTY;
}
