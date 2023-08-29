package com.turinglv.cat;

import java.lang.annotation.*;

/**
 * Cat.logMetricForCount 注解
 *
 * @author tlv
 * @since 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CatMetric {

	/**
	 * 指标名称
	 *
	 * @return 名称
	 */
	String name() default "";

	/**
	 * 标签键值对
	 *
	 * @return 标签键值对
	 */
	String[] tags() default {};

	/**
	 * 计数
	 *
	 * @return 计数
	 */
	int count() default 0;

	/**
	 * 数量
	 *
	 * @return 数量
	 */
	String quantity() default "";

	/**
	 * 耗时（毫秒）
	 *
	 * @return 耗时（毫秒）
	 */
	String durationInMillis() default "";

	/**
	 * 总和
	 *
	 * @return 总和
	 */
	String sum() default "";
}
