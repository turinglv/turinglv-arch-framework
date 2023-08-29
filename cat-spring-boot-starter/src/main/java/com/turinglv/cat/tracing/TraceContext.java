package com.turinglv.cat.tracing;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.dianping.cat.Cat;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;

/**
 * 链路上下文
 *
 * @author tlv
 * @since 1.0.0
 */
public class TraceContext implements Cat.Context {

	public static final String TRACE_ID = "traceId";

	private static final TransmittableThreadLocal<Cat.Context> holder = new TransmittableThreadLocal<>();

	private final Map<String, String> properties = Maps.newHashMap();

	@Override
	public void addProperty(String key, String value) {
		if (key == null || value == null) {
			return;
		}

		((TraceContext) getContext()).properties.put(key, value);
	}

	@Override
	public String getProperty(String key) {
		if (key == null) {
			return null;
		}

		return ((TraceContext) getContext()).properties.get(key);
	}

	public static String getTraceId() {
		return StringUtils.trimToEmpty(getContext().getProperty(Cat.Context.ROOT));
	}

	public static Cat.Context getContext() {
		Cat.Context context = holder.get();
		if (context == null) {
			context = new TraceContext();
			holder.set(context);
		}
		return context;
	}

	public static void remove(String id) {
		if (id == null) {
			remove();
		}
		Cat.Context context = holder.get();
		if (context != null) {
			String traceId = context.getProperty(Cat.Context.ROOT);
			if (Objects.equals(id, traceId)) {
				remove();
			}
		}
	}

	public static void remove() {
		if (holder.get() != null) {
			holder.remove();
		}
	}
}
