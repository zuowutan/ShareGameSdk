package com.example.sdklibrary.tools;
import java.util.regex.Pattern;
/**
 * Created by tzw on 2018/6/5.
 */

public class StringUtils {

    private final static Pattern PATTERN_LETTER_NUMER_UNDERLINE = Pattern.compile( "^[A-Z_a-z0-9]{1,}$" );// 匹配：字母、数字、下划线
    private final static Pattern PATTERN_LETTER_NUMER = Pattern.compile( "^[A-Za-z0-9]{1,}$" );// 匹配：字母、数字、下划线
    private final static Pattern PATTERN_NUMBER = Pattern.compile( "^[0-9]*$" ); // 匹配：数字
    public static boolean isEmpty( String s ) {
        return s==null ? true : s.trim().equals( "" );
    }

    public static boolean isNotEmpty( String s ) {
        return s==null ? false : !s.trim().equals( "" );
    }
    /**
     * 判断字符串是否是：字母、数字,下滑线
     * @param str
     * @return
     */
    public static boolean isLetterOrNumerOrUnderline( String str ) {
        if( str==null ){
            return false;
        }
        return PATTERN_LETTER_NUMER_UNDERLINE.matcher( str ).matches();
    }
    /**
     * 判断字符串是否是：字母、数字
     * @param str
     * @return
     */
    public static boolean isLetterOrNumer( String str ) {
        if( str==null ){
            return false;
        }
        return PATTERN_LETTER_NUMER.matcher( str ).matches();
    }

    /**
     * 判断字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumber( String str ) {
        if( str==null ){
            return false;
        }
        return PATTERN_NUMBER.matcher( str ).matches();
    }


    /**
     * 字符串长度在某个范围之内
     * @param str
     * @param minLength
     * @param maxLength
     * @return
     */
    public static boolean isBetween( String str,int minLength,int maxLength ) {
        if( isEmpty( str ) ){
            return false;
        }
        return str.trim().length()>=minLength&&str.trim().length()<=maxLength;
    }

}
