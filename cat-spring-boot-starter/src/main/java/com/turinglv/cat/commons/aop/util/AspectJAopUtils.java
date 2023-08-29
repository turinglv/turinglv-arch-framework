package com.turinglv.cat.commons.aop.util;

import com.turinglv.cat.commons.format.MessageFormatUtils;
import com.turinglv.cat.expression.SpelExpressionEvaluator;
import lombok.experimental.UtilityClass;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.Assert;

import java.lang.reflect.Method;

/**
 * AspectJ 工具
 *
 * @author tlv
 * @since 1.0.0
 */
@UtilityClass
public class AspectJAopUtils extends org.springframework.aop.aspectj.AspectJAopUtils {

	private static final String SPEL_EXPRESSION_IS_INVALID = "Spel expression is invalid: {}";

	/**
	 * 从切点获取 Method
	 *
	 * @param joinPoint
	 * @return
	 */
	public static Method getMethod(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		if (method.getDeclaringClass().isInterface()) {
			try {
				method = joinPoint.getTarget().getClass().getDeclaredMethod(joinPoint.getSignature().getName(),
					method.getParameterTypes());
			} catch (SecurityException | NoSuchMethodException e) {
				throw new RuntimeException(e);
			}
		}
		return method;
	}

	/**
	 * 解析 Spel 表达式
	 *
	 * @param expressionString
	 * @param joinPoint
	 * @return
	 */
	public static String parseExpression(String expressionString, JoinPoint joinPoint) {
		Object[] arguments = joinPoint.getArgs();
		Method method = getMethod(joinPoint);
		String resolveValue = SpelExpressionEvaluator.parseExpression(expressionString, method, arguments);
		Assert.notNull(resolveValue, MessageFormatUtils.format(SPEL_EXPRESSION_IS_INVALID, expressionString));
		return resolveValue;
	}
}
