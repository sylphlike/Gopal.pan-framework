package com.github.sylphlike.framework.utils.sequence;

import java.util.UUID;

/**
 * <p>  time 10/09/2020 18:19  星期四 (dd/MM/YYYY HH:mm) 
 * <p> email 15923508369@163.com 
 *
 * 随机不重复序列
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public class RandomSequence {

    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };



    /**
     * <p>  time 13:50 2020/10/8 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com 
     *  十位随机不重复数字序列，不到十位的前面补0
     * @param
     * @return java.lang.String
     * @author Gopal.pan
     */
    public static String digitalId(){
        String s = UUID.randomUUID().toString();
        String replace = s.replace("-", "");
        int abs = Math.abs(replace.hashCode());
        return String.format("%10d", abs).replace(" ", "0");
    }



    /**
     * <p>  time 13:45 2020/10/8 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com 
     * 8位不重复ID
     *  通过随机生成32位UUID，由于UUID都为十六进制，所以将UUID分成8组，每4个为一组，然后通过模62操作，结果作为索引取出字符
     *
     * @param
     * @return java.lang.String
     * @author Gopal.pan
     */
    public static String eightUuid() {
        StringBuilder shortBuffer = new StringBuilder();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();

    }





}
