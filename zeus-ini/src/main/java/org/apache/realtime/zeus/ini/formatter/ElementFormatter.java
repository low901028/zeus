package org.apache.realtime.zeus.ini.formatter;

import org.apache.realtime.zeus.ini.ElementFormattable;
import org.apache.realtime.zeus.ini.element.IniElement;

/**
 * <pre> elementFormatter, format a String value as {@link IniElement}.
 * <pre> Other than CommentElementFormatter, All element formatters should additionally require a comment element formatter
 * 
 */
public interface ElementFormatter<E extends IniElement> extends ElementFormattable<E> {

    /**
     * check this value.
     * @param value value
     * @return true if can.
     */
    boolean check(String value);

    /**
     * <pre> this method will not check value, so you should {@link #check(String)} first.
     * <pre> However, not checking will not necessarily report an error, but may result in non-compliance.
     * @param value a String value
     * @param line line number
     * @return {@link E}, can not be null.
     */
    @Override
    E format(String value, int line);

}
