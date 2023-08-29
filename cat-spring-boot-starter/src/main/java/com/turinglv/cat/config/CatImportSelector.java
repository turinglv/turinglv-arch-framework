package com.turinglv.cat.config;

import com.turinglv.cat.EnableCat;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;
import org.springframework.context.annotation.AutoProxyRegistrar;

/**
 * EnableCat 装配选择器
 *
 * @author tlv
 * @since 2.4.x
 */
public class CatImportSelector extends AdviceModeImportSelector<EnableCat> {

	@Override
	protected String[] selectImports(AdviceMode adviceMode) {
		switch (adviceMode) {
			case PROXY:
				return new String[]{
					AutoProxyRegistrar.class.getName(),
					CatConfiguration.class.getName()
				};
			case ASPECTJ:
				return new String[]{
					CatConfiguration.class.getName()
				};
		}
		return new String[0];
	}
}
