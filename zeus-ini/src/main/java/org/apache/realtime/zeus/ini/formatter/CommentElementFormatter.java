package org.apache.realtime.zeus.ini.formatter;


import org.apache.realtime.zeus.ini.element.IniComment;
import org.apache.realtime.zeus.ini.element.IniCommentImpl;

/**
 *
 * an element formatter for comment
 *
 * 
 */
public class CommentElementFormatter extends BaseElementFormatter<IniComment> {

    /** comment start char, {@link IniComment#HEAD} */
    private char start;

    public CommentElementFormatter(char startChar){
        super(null);
        start = startChar;
    }
    public CommentElementFormatter(){
        super(null);
        start = IniComment.HEAD;
    }

    /**
     * format a String value as {@link IniComment}.
     * @param value a String value
     * @return {@link IniComment}
     */
    @Override
    public IniComment format(String value, int line) {
        return new IniCommentImpl(value.substring(1), value, line);
    }

    /**
     * check value if can
     * @param value a String value
     */
    @Override
    public boolean check(String value){
        // if start with '#'
        return value.charAt(0) == start;
    }

}
