package org.apache.realtime.zeus.core.spi.filter;


import org.apache.realtime.zeus.spi.SPI;

/**
 * <p>SPI Interface </p>
 */
@SPI
public interface Filter {

	/**
	 * 模拟执行方法
	 */
	String doFilter();

}
