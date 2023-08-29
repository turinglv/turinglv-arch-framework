package com.turinglv.cat.constant;

import lombok.experimental.UtilityClass;

/**
 * Spring Properties 常量定义
 *
 * @author tlv
 * @since 1.0.0
 */
@UtilityClass
public final class SpringProperties {

	/**
	 * Spring Boot 读取应用程序名称
	 */
	public static final String SPRING_APPLICATION_NAME = "spring.application.name";

	/**
	 * Spring Boot 读取应用程序名称
	 */
	public static final String NAME_PATTERN = "${spring.application.name:${vcap.application.name:${spring.config.name:application}}}";

	/**
	 * Spring Boot 读取应用程序启动端口
	 */
	public static final String PORT_PATTERN = "${server.port}";

	/**
	 * Spring Boot 读取应用程序索引
	 */
	public static final String INDEX_PATTERN = "${vcap.application.instance_index:${spring.application.index:${server.port:${PORT:null}}}}";

	/**
	 * Spring Boot 默认运行环境
	 */
	public static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";

	/**
	 * Spring Boot 指定运行环境
	 */
	public static final String SPRING_PROFILE_ACTIVE = "spring.profiles.active";
}
