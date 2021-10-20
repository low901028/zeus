package org.apache.realtime.zeus.core.cep;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * 动态加载cep
 */
public class DynamicFlinkCep  extends CEP {
    private static final Logger logger = LoggerFactory.getLogger(DynamicFlinkCep.class);

    public static <T> PatternStream<T> dynamicPattern(final DataStream<T> input, DynamicPatternSelector<T> selector) {
        Pattern<T, ?> pattern = null;
        if (selector.getPeriod() != 0) {
            ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);
            ScheduledFuture<Pattern<T, ?>> f =
                    scheduledExecutor.schedule(()->getPattern(selector), selector.getPeriod(), TimeUnit.MILLISECONDS);
            Runtime.getRuntime().addShutdownHook(new Thread(()->scheduledExecutor.shutdown()));
            try {
                pattern = f.get();
            } catch (InterruptedException e) {
                logger.error("interruted exception: {}", e);
            } catch (ExecutionException e) {
                logger.error("execution exception: {}", e);
            }
        } else {
            pattern = getPattern(selector);
        }

        return CEP.pattern(input, pattern);
    }

    static private <T> Pattern<T, ?> getPattern(DynamicPatternSelector<T> selector) {
        Pattern<T, ?> pattern = null;


        try {
            Pair<String, Pattern<T, ?>> pair = selector.select();
            pattern = pair.getRight();
        } catch (Exception e) {
            logger.error("Failed to load dynamic pattern", e);
            throw  new RuntimeException("Failed to load dynamic pattern");
        }

        return pattern;
    }
}
