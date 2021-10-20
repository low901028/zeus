package org.apache.realtime.zeus.ini;

import org.apache.realtime.zeus.ini.element.IniElement;

import java.io.Closeable;

/**
 * <pre> ini formatter.
 * <pre> Generally the formatter requires three chars like
 * {@link IniReader#SECTIONS_HEAD},
 * {@link IniReader#SECTIONS_END},
 * {@link IniReader#PARAMETER_SPLIT}
 * 
 */
public interface IniFormatter extends Closeable, Initialisable {

    /** the new line separator on current system. */
    String NEW_LINE_SEPARATOR = System.getProperty("line.separator", "\n");

    /**
     * format a line as a element
     * @param line line string
     * @return {@link IniElement}
     */
    IniElement formatLine(String line);

    /**
     * by default, {@code close() = init()}
     */
    @Override
    default void close() {
        init();
    }

}
