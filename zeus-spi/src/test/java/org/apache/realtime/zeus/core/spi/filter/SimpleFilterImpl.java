package org.apache.realtime.zeus.core.spi.filter;

import org.apache.realtime.zeus.spi.Extensions;

/**
 * <p>无参数 + 单例 实现</p>
 */
@Extensions
public class SimpleFilterImpl implements Filter {
	@Override
	public String doFilter() {
		return this.getClass().getName() + " hashCode: " + this.hashCode();
	}
}
