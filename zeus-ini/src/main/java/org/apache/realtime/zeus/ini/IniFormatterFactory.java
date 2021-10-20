package org.apache.realtime.zeus.ini;

import org.apache.realtime.zeus.ini.element.IniComment;
import org.apache.realtime.zeus.ini.element.IniProperty;
import org.apache.realtime.zeus.ini.element.IniSection;
import org.apache.realtime.zeus.ini.formatter.ElementFormatter;

/**
 * <pre> function interface for iniFormatter
 * <pre> Generally the formatter requires three chars like
 * {@link IniReader#SECTIONS_HEAD},
 * {@link IniReader#SECTIONS_END},
 * {@link IniReader#PARAMETER_SPLIT}
 * 
 */
public interface IniFormatterFactory {

    /**
     * get an iniFormatter by three chars.
     * @param commentElementFormatter  a formatter for comment
     * @param sectionElementFormatter  a formatter for section
     * @param propertyElementFormatter a formatter for property
     * @return an {@link IniFormatter}
     */
    IniFormatter apply(ElementFormatter<IniComment> commentElementFormatter,
                       ElementFormatter<IniSection> sectionElementFormatter,
                       ElementFormatter<IniProperty> propertyElementFormatter);

}
