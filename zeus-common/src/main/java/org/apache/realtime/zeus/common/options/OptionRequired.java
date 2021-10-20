package org.apache.realtime.zeus.common.options;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OptionRequired {
    boolean required() default false;
    boolean hasArg() default true;
    String description() default "";
}
