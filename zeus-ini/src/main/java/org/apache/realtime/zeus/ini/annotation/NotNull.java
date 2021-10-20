package org.apache.realtime.zeus.ini.annotation;

import java.lang.annotation.*;

/**
 * An identifier that can not be null.
 * 
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
public @interface NotNull { }
