package org.apache.realtime.zeus.core.cep;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.flink.api.common.functions.AbstractRichFunction;
import org.apache.flink.cep.pattern.Pattern;

public abstract class DynamicPatternSelector<T> extends AbstractRichFunction {
    private static final long serialVersionUID = 1L;

    /**
     * pattern
     * @return
     * @throws Exception
     */
    public abstract Pair<String/*namespace*/, Pattern<T, ?>> select() throws Exception;

    /**
     * 单位：ms
     * @return
     */
    public abstract long getPeriod();
}
