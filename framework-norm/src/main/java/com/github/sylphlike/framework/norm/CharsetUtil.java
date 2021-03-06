package com.github.sylphlike.framework.norm;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * <p>  time 17:56 2018/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */

public class CharsetUtil {




    public static final char CHAR_ENGLISH_COMMA = ',';
    public static final char CHAR_ENGLISH_UNDERLINE = '_';
    public static final char CHAR_ENGLISH_DASHED = '-';

    public static final char CHAR_ENGLISH_SPACE = ' ';
    public static final char CHAR_ENGLISH_DOT = '.';
    public static final char CHAR_ENGLISH_SLASH = '/';

    public static final char CHAR_ENGLISH_COLON = ':';
    public static final char CHAR_ENGLISH_SEMICOLON = ';';



    public static final char CHAR_ENGLISH_SINGLE_QUOTE = '\'';
    public static final char CHAR_ENGLISH_BACKSLASH = '\\';
    public static final char CHAR_ENGLISH_CR = '\r';
    public static final char CHAR_ENGLISH_LF = '\n';
    public static final char CHAR_ENGLISH_TAB = '	';
    public static final char CHAR_ENGLISH_BRACKET_START = '[';
    public static final char CHAR_ENGLISH_BRACKET_END = ']';
    public static final char CHAR_ENGLISH_PARENTHESES_START = '(';
    public static final char CHAR_ENGLISH_PARENTHESES_END = ')';



    public static final String STRING_AND ="&";
    public static final String STRING_DOUBT ="?";
    public static final String STRING_EQUAL ="=";
    public static final String CHAR_ENGLISH_EMPTY = "";
    public static final String STRING_ENGLISH_COMMA =",";
    public static final String STRING_ENGLISH_COMMA_SPACE =", ";
    public static final String STRING_ENGLISH_SLASH_SPACE = " / ";
    public static final String STRING_ENGLISH_VERTICAL_SPACE = " | ";
    public static final String STRING_ENGLISH_SEMICOLON_SPACE = "; ";
    public static final String STRING_ENGLISH_DOT = ".";


     /** 系统默认字符集编码 */
    public static Charset defaultCharset() {
        return Charset.defaultCharset();
    }


    /**
     * 是否为Windows或者Linux（Unix）文件分隔符
     * <p>  time 18:26 2018/1/29      </p>
     * <p> email 15923508369@163.com  </p>
     * @param c 字符
     * @return 是否为Windows或者Linux（Unix）文件分隔符
     * @author  Gopal.pan
     */
    public static boolean isFileSeparator(char c) {
        return CHAR_ENGLISH_SLASH == c || CHAR_ENGLISH_BACKSLASH == c;
    }


    /**
     * 字符串UTF-8编码
     * <p>  time 18:27 2018/1/29      </p>
     * <p> email 15923508369@163.com  </p>
     * @param originString  原始字符串
     * @return utf-8 编码后的字符串
     * @author  Gopal.pan
     */
    public static String toUtf8String(String originString) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < originString.length(); i++) {
            char c = originString.charAt(i);
            if (c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes(StandardCharsets.UTF_8);
                } catch (Exception ex) {
                    b = new byte[0];
                }
                for (int value : b) {
                    int k = value;
                    if (k < 0)
                        k += 256;
                    sb.append("%").append(Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }
}
