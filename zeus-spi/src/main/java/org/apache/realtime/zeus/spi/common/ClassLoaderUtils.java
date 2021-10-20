package org.apache.realtime.zeus.spi.common;

/**
 * <p>Classloader工具类</p>
 */
public class ClassLoaderUtils {

	/**
	 * 获取 Classloader 默认获取当前线程的classloader 其次获取中间件的classloader 最后获取系统的 classloader
	 *
	 * @return
	 */
	public static ClassLoader getCurrentClassLoader() {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if (loader != null) {
			return loader;
		}

		loader = ClassLoader.class.getClassLoader();
		if (loader != null) {
			return loader;
		}

		return ClassLoader.getSystemClassLoader();
	}

}
