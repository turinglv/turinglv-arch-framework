package com.turinglv.cat.spring.boot.autoconfigure;

import com.dianping.cat.Cat;
import com.turinglv.cat.EnableCat;
import com.turinglv.cat.commons.lang.Strings;
import com.turinglv.cat.config.CatState;
import com.turinglv.cat.constant.Conditions;
import com.turinglv.cat.constant.SpringProperties;
import com.turinglv.cat.env.CatProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

/**
 * CAT 自动装配
 *
 * @author tlv
 * @since 1.0.0
 */
@ConditionalOnProperty(
	prefix = CatProperties.PREFIX,
	name = Conditions.ENABLED,
	havingValue = Conditions.TRUE
)
@ConditionalOnClass(Cat.class)
@EnableCat
@EnableConfigurationProperties(CatProperties.class)
@RequiredArgsConstructor
@Slf4j
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@Configuration(proxyBeanMethods = false)
public class CatAutoConfiguration implements InitializingBean {

	private static final String INITIALIZING_CAT_CLIENT = "Initializing cat client";

	private static final String CAT_HOME = "CAT_HOME";

	private final CatProperties catProperties;

	private final Environment environment;

	@Override
	public void afterPropertiesSet() {
		log.debug(INITIALIZING_CAT_CLIENT);

		String servers = catProperties.getServers();
		Assert.notNull(servers, "cat servers is not null");

		// 动态设置 cat-home 路径
		System.setProperty(CAT_HOME, catProperties.getHome());

		// 代替 META-INF/app.properties
		String domain;
		if (StringUtils.isBlank(catProperties.getDomain())) {
			domain = environment.getProperty(SpringProperties.SPRING_APPLICATION_NAME);
		} else {
			domain = catProperties.getDomain();
		}

		// 初始化
		Cat.initializeByDomain(domain,
			catProperties.getTcpPort(),
			catProperties.getHttpPort(),
			servers.split(Strings.COMMA));

		if (catProperties.isTraceMode()) {
			Cat.getManager().setTraceMode(true); // 这个感觉有Bug
		}

		CatState.setInitialized();
	}
}
