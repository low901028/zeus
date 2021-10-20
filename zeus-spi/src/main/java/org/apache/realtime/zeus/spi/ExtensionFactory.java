package org.apache.realtime.zeus.spi;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>扩展点加载器工厂</p>
 */
public class ExtensionFactory {
	private ExtensionFactory() {
	}

	private static final Map<Class, ExtensionLoader> EXTENSION_LOADER_MAP = new ConcurrentHashMap<>();

	/**
	 * 获取指定接口的扩展点加载器
	 *
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> ExtensionLoader<T> getExtensionLoader(Class<T> clazz) {
		ExtensionLoader loader = EXTENSION_LOADER_MAP.get(clazz);
		if (loader == null) {
			synchronized (ExtensionFactory.class) {
				loader = EXTENSION_LOADER_MAP.get(clazz);
				if (loader == null) {
					EXTENSION_LOADER_MAP.putIfAbsent(clazz, new ExtensionLoader(clazz));
					loader = EXTENSION_LOADER_MAP.get(clazz);
				}
			}
		}
		return loader;
	}
}
