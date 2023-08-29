package com.turinglv.cat.env;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Cat 属性配置
 *
 * @author tlv
 * @since 1.0.0
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "cat")
public class CatProperties {

	public static final String PREFIX = "cat";

	private boolean enabled;

	private boolean traceMode = false;

	private boolean supportOutTraceId = false;

	private String home = "/tmp";

	private String domain;

	private String servers;

	private int tcpPort = 2280;

	private int httpPort = 80;

	private final Http http = new Http();

	private final Dubbo dubbo = new Dubbo();

	private final Mybatis mybatis = new Mybatis();

	@Getter
	@Setter
	public static class Http {

		private boolean enabled = true;

		private String excludeUrls = "/favicon.ico,/js/*,/css/*,/image/*";

		private String includeHeaders;

		private boolean includeBody = false;
	}

	@Getter
	@Setter
	public static class Dubbo {

		private boolean enabled = true;
	}

	@Getter
	@Setter
	public static class Mybatis {

		private boolean enabled = true;
	}
}
