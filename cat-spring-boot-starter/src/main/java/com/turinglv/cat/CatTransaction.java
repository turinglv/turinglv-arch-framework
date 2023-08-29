package com.turinglv.cat;

import java.lang.annotation.*;

/**
 * Transaction 注解
 *
 * @author tlv
 * @since 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CatTransaction {
}
