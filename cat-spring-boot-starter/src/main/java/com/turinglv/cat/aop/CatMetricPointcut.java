package com.turinglv.cat.aop;

import com.turinglv.cat.CatMetric;
import com.turinglv.cat.CatMetrics;
import com.turinglv.cat.commons.reflect.ReflectionUtils;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;

/**
 * CatLogMetricForCount 切点
 *
 * @author tlv
 * @since 1.0.0
 */
public class CatMetricPointcut extends StaticMethodMatcherPointcut {

	/**
	 * 匹配切点
	 *
	 * @param method      调用方法
	 * @param targetClass 目标类
	 * @return 是否匹配
	 */
	@Override
	public boolean matches(Method method, Class<?> targetClass) {
		if (!ReflectionUtils.isPublic(method)) {
			return false;
		}

		Method specificMethod = ClassUtils.getMostSpecificMethod(method, targetClass);
		specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);
		return matchesCatLogMetricForCount(method) ||
			matchesCatLogMetricForCount(specificMethod) ||
			matchesCatLogMetricForCounts(method) ||
			matchesCatLogMetricForCounts(specificMethod);
	}

	/**
	 * 判断是否匹配 {@code CatLogMetricForCount} 注解
	 *
	 * @param method 调用方法
	 * @return 是否匹配
	 */
	private boolean matchesCatLogMetricForCount(Method method) {
		return !AnnotatedElementUtils.findAllMergedAnnotations(method, CatMetric.class).isEmpty();
	}

	/**
	 * 判断是否匹配 {@code CatLogMetricForCounts} 注解
	 *
	 * @param method 调用方法
	 * @return 是否匹配
	 */
	private boolean matchesCatLogMetricForCounts(Method method) {
		return !AnnotatedElementUtils.findAllMergedAnnotations(method, CatMetrics.class).isEmpty();
	}
}
