package org.apache.realtime.zeus.spi;

import java.lang.annotation.*;

/**
 * <p>扩展SPI实现注解</p>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Extensions {

	/**
	 * 是否单例模式，默认为 true
	 *
	 * @return
	 */
	boolean singleton() default true;

}
