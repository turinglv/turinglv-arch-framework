package com.turinglv.cat;

import java.lang.annotation.*;

/**
 * CatMetric 注解集
 *
 * @author tlv
 * @since 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CatMetrics {

	CatMetric[] value();
}
