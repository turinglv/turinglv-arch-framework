package com.turinglv.cat;

import com.turinglv.cat.config.CatImportSelector;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;

import java.lang.annotation.*;

/**
 * 开启 CAT 埋点
 *
 * @author tlv
 * @since 1.0.0
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({CatImportSelector.class})
public @interface EnableCat {

	/**
	 * 是否开启 CGLIB 代理
	 *
	 * @return 是否开启 CGLIB 代理
	 */
	boolean proxyTargetClass() default false;

	/**
	 * PointcutAdvisor 代理模式
	 *
	 * @return 代理模式
	 * @see org.springframework.context.annotation.AdviceModeImportSelector
	 */
	AdviceMode mode() default AdviceMode.PROXY;

	/**
	 * PointcutAdvisor 优先级
	 *
	 * @return 优先级
	 */
	int order() default Ordered.LOWEST_PRECEDENCE;
}
