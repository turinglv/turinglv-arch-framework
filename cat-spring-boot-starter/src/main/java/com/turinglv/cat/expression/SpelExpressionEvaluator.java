package com.turinglv.cat.expression;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.lang.reflect.Method;

/**
 * SpEL 表达式解析器
 *
 * @author tlv
 * @since 1.0.0
 */
public class SpelExpressionEvaluator {

	private static final ExpressionParser PARSER = new SpelExpressionParser();

	private static final ParameterNameDiscoverer DISCOVERER = new LocalVariableTableParameterNameDiscoverer();

	public static ExpressionParser getExpressionParser() {
		return PARSER;
	}

	public static ParameterNameDiscoverer getParameterNameDiscoverer() {
		return DISCOVERER;
	}

	/**
	 * 解析 SpEL 表达式
	 *
	 * @param expressionString SpEL 表达式
	 * @param method           方法
	 * @param arguments        参数
	 * @return 解析后的内容
	 */
	public static String parseExpression(String expressionString, Method method, Object[] arguments) {
		String[] params = DISCOVERER.getParameterNames(method);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				SpelEvaluationContext.setVariable(params[i], arguments[i]);
			}
		}

		Expression expression = PARSER.parseExpression(expressionString);
		return expression.getValue(SpelEvaluationContext.getContext(), String.class);
	}
}
