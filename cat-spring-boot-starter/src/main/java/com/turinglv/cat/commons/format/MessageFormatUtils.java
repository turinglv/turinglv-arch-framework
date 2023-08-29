package com.turinglv.cat.commons.format;

import lombok.experimental.UtilityClass;

/**
 * 消息格式化工具
 *
 * @author tlv
 * @since 1.0.0
 */
@UtilityClass
public class MessageFormatUtils {

	/**
	 * 格式化消息内容
	 *
	 * @param message      消息
	 * @param placeholders 占位符
	 * @return 格式化内容
	 */
	public static String format(String message, Object... placeholders) {
		return MessageFormatter.arrayFormat(message, placeholders).getMessage();
	}
}
