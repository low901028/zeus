package org.apache.realtime.zeus.spi;

import java.lang.annotation.*;

/**
 * <p>扩展点接口标示</p>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SPI {

	/**
	 * 缺省扩展点名称
	 *
	 * @return
	 */
	String value() default "default";

}
