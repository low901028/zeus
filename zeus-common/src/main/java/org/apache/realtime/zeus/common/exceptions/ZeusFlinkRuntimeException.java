package org.apache.realtime.zeus.common.exceptions;

import org.apache.flink.util.FlinkRuntimeException;

/**
 *
 */
public class ZeusFlinkRuntimeException extends FlinkRuntimeException {
    public ZeusFlinkRuntimeException(String message) {
        super(message);
    }

    public ZeusFlinkRuntimeException(Throwable cause) {
        super(cause);
    }

    public ZeusFlinkRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
