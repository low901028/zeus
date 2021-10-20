package org.apache.realtime.zeus.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * stacktrace convert to string
 */
public class ExceptionUtils {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionUtils.class);

    public static String getErrorMessage(Throwable e) {
        if (null == e) {
            return null;
        }

        try (StringWriter stringWriter = new StringWriter();
             PrintWriter writer = new PrintWriter(stringWriter)) {
            e.printStackTrace(writer);
            writer.flush();
            stringWriter.flush();
            StringBuffer buffer = stringWriter.getBuffer();
            return buffer.toString();
        } catch (Throwable ee) {
            logger.error("", ee);
        }
        return null;
    }
}