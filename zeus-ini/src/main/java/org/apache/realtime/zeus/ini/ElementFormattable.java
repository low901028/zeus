package org.apache.realtime.zeus.ini;

import org.apache.realtime.zeus.ini.element.IniElement;

/**
 *
 * Element formattable.
 * used by
 *
 * 
 */
public interface ElementFormattable<E extends IniElement> {

    /**
     * 
     * format String value as IniElement
     * @param value a String value
     * @param line line number
     * @return {@link IniElement}
     */
    E format(String value, int line);

}
