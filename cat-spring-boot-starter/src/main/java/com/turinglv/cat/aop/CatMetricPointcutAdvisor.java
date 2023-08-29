package com.turinglv.cat.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;

/**
 * CatLogMetricForCount 切点声明
 *
 * @author tlv
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Slf4j
public class CatMetricPointcutAdvisor extends AbstractBeanFactoryPointcutAdvisor {

	private final CatMetricPointcut pointcut = new CatMetricPointcut();

	/**
	 * 获取切点
	 *
	 * @return 切点
	 */
	@Override
	public Pointcut getPointcut() {
		return pointcut;
	}
}
