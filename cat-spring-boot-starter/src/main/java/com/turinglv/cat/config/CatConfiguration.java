package com.turinglv.cat.config;

import com.turinglv.cat.EnableCat;
import com.turinglv.cat.aop.CatMetricInterceptor;
import com.turinglv.cat.aop.CatMetricPointcutAdvisor;
import com.turinglv.cat.aop.CatTransactionMethodInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.context.annotation.Role;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Cat 配置
 *
 * @author tlv
 * @since 2.4.x
 */
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@Slf4j
@Configuration(proxyBeanMethods = false)
public class CatConfiguration implements ImportAware {

	public static final String IMPORTING_META_NOT_FOUND = "@EnableCat is not present on importing class";

	private AnnotationAttributes enableCat;

	@Override
	public void setImportMetadata(AnnotationMetadata importMetadata) {
		this.enableCat = AnnotationAttributes.fromMap(
			importMetadata.getAnnotationAttributes(EnableCat.class.getName(), false));
		if (this.enableCat == null) {
			log.warn(IMPORTING_META_NOT_FOUND);
		}
	}

	@Bean
	public CatMetricInterceptor catLogMetricForCountInterceptor() {
		return new CatMetricInterceptor();
	}

	@Bean
	public CatMetricPointcutAdvisor CatLogMetricForCountPointcutAdvisor(
		CatMetricInterceptor CatMetricInterceptor) {
		CatMetricPointcutAdvisor pointcutAdvisor = new CatMetricPointcutAdvisor();
		pointcutAdvisor.setAdviceBeanName("CatLogMetricForCountPointcutAdvisor");
		pointcutAdvisor.setAdvice(CatMetricInterceptor);
		if (enableCat != null) {
			pointcutAdvisor.setOrder(enableCat.getNumber("order"));
		}
		return pointcutAdvisor;
	}

	@Bean
	public CatTransactionMethodInterceptor catTransactionMethodInterceptor() {
		return new CatTransactionMethodInterceptor();
	}
}
