package com.turinglv.cat.spi;

import com.dianping.cat.CatPropertyProvider;
import org.unidal.helper.Properties;

/**
 * 自定义 CatPropertyProvider
 *
 * @author tlv
 * @since 1.0.0
 */
public class CustomCatPropertyProvider implements CatPropertyProvider {

	private final Properties.PropertyAccessor<String> config;

	public CustomCatPropertyProvider() {
		super();
		config = Properties.forString().fromEnv().fromSystem();
	}

	public String getProperty(final String name, final String defaultValue) {
		return config.getProperty(name, defaultValue);
	}
}
