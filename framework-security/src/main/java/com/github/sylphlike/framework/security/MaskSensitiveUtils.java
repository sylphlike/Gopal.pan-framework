package com.github.sylphlike.framework.security;


import org.apache.commons.lang3.StringUtils;


/**
 * 敏感信息掩码工具类
 * <p>  time 10/09/2020 18:19  星期四 (dd/MM/YYYY HH:mm)
 * <p> email 15923508369@163.com
 * @author Gopal.pan
 * @version 1.0.0
 */

public class MaskSensitiveUtils {


    /**
     * 中文名称 只显示第一个汉字,其他隐藏为 *
     * <p>  time 17:40 2021/1/4 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param chineseName  明文中文名称
     * @return  java.lang.String
     * @author  Gopal.pan
     */
    public static String chineseName(String chineseName){
        if(StringUtils.isEmpty(chineseName)) { return ""; }
        return StringUtils.rightPad(StringUtils.left(chineseName,1),chineseName.length(),"*");
    }



    /**
     * 身份证号 显示最后四位，其他隐藏。共计18位或者15位
     * <p>  time 17:40 2021/1/4 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param idCardNum  明文身份号
     * @return  java.lang.String
     * @author  Gopal.pan
     */
    public static String idCardNum(String idCardNum){
        if(StringUtils.isEmpty(idCardNum)) { return ""; }
        return StringUtils.leftPad(StringUtils.right(idCardNum,4),idCardNum.length(),"*");
    }


    /**
     * 固定电话 显示最后四位
     * <p>  time 17:39 2021/1/4 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param fixedPhone  明文固定电话号码
     * @return  java.lang.String
     * @author  Gopal.pan
     */
    public static String fixedPhone(String fixedPhone){
        if(StringUtils.isEmpty(fixedPhone)) { return ""; }
        return StringUtils.leftPad(StringUtils.right(fixedPhone,4),fixedPhone.length(),"*");
    }



    /**
     * 移动电话,显示前三后四   159****8369
     * <p>  time 17:39 2021/1/4 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param mobilePhone     明文移动电话号码
     * @return  java.lang.String
     * @author  Gopal.pan
     */
    public static String mobilePhone(String mobilePhone){
        if(StringUtils.isEmpty(mobilePhone)) { return ""; }
        String rightPad = StringUtils.rightPad(StringUtils.left(mobilePhone, 3), mobilePhone.length() - 4, "*");
        return rightPad.concat(mobilePhone.substring(7,mobilePhone.length()));
    }


    /**
     * 电子邮箱  邮箱前缀显示三位@及后面的地址显示 159********@163.com
     * <p>  time 17:37 2021/1/4 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param email  明文电子邮箱
     * @return  java.lang.String
     * @author  Gopal.pan
     */
    public static String email(String email){
        if(StringUtils.isEmpty(email)) { return ""; }
        int index = StringUtils.indexOf(email, "@");
        return StringUtils.rightPad(StringUtils.left(email, 3), index, "*").concat(email.substring(index,email.length()));
    }



    /**
     * 银行卡号，前六后四  621661*********7010
     * <p>  time 17:33 2021/1/4 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param bankCard  银行卡号
     * @return  java.lang.String
     * @author  Gopal.pan
     */
    public static String bankCard64(String bankCard){
        if(StringUtils.isEmpty(bankCard)){ return ""; }

        String rightPad = StringUtils.rightPad(StringUtils.left(bankCard, 6), bankCard.length() - 4, "*");
        return rightPad.concat(bankCard.substring(15,bankCard.length()));

    }



    /**
     * 银行卡号，前一后四 6*****************7010
     * <p>  time 17:32 2021/1/4 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param bankCard  银行卡号
     * @return  java.lang.String
     * @author  Gopal.pan
     */
    public static String bankCard14(String bankCard){
        if(StringUtils.isEmpty(bankCard)){ return ""; }

        String rightPad = StringUtils.rightPad(StringUtils.left(bankCard, 1), bankCard.length() - 1, "*");
        return rightPad.concat(bankCard.substring(15,bankCard.length()));

    }


}
