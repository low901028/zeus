package org.apache.realtime.zeus.ini.element;

/**
 * Ini file's comment.
 *
 * 
 */
public class IniCommentImpl extends BaseElement implements IniComment {
    private static final long serialVersionUID = 0xFF02FFFF;


    /** constructor */
    public IniCommentImpl(String value, String originalValue, int lineNumber){
        super(value, originalValue, lineNumber);
    }

    /** constructor */
    public IniCommentImpl(String originalValue, int lineNumber){
        super(originalValue.substring(1), originalValue, lineNumber);
    }

    /**
     * Get instance only based on value
     * @param value      value
     * @param lineNumber line number
     */
    public static IniCommentImpl byValue(String value, int lineNumber){
        return new IniCommentImpl(value, HEAD + value, lineNumber);
    }

    /**
     * If the value changed, change the originalValue
     *
     * @param newValue when value changes, like {@link #setValue(String)} or {@link #setValue(java.util.function.Function)}
     */
    @Override
    protected String valueChanged(String newValue) {
        return "# " + trim(newValue);
    }

}
