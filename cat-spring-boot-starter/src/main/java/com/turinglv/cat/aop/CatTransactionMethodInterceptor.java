package com.turinglv.cat.aop;

import com.turinglv.cat.CatClient;
import com.turinglv.cat.CatConstants;
import com.turinglv.cat.commons.lang.Strings;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.AopProxyUtils;


import java.lang.reflect.Method;
import java.util.Objects;

/**
 * CatTransaction 方法拦截
 *
 * @author tlv
 * @since 1.0.0
 */
public class CatTransactionMethodInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Method method = invocation.getMethod();
		Object target = invocation.getThis();
		Class<?> targetClass = AopProxyUtils.ultimateTargetClass(Objects.requireNonNull(target));
		String name = targetClass.getSimpleName() + Strings.DOT + method.getName();
		return CatClient.newTransaction(CatConstants.TYPE_INNER_SERVICE, name, () -> {
			try {
				return invocation.proceed();
			} catch (Throwable e) {
				throw new RuntimeException(e);
			}
		});
	}
}

