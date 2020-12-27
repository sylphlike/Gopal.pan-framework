package com.github.sylphlike.framework.security;


import org.apache.commons.lang3.StringUtils;


/**
 * <p>  time 10/09/2020 18:19  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * 敏感信息掩码工具类
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public class MaskSensitiveUtils {


    /**
     * 中文名称 只显示第一个汉字,其他隐藏为 *
     * @param chineseName
     * @return
     */
    public static String chineseName(String chineseName){
        if(StringUtils.isEmpty(chineseName)) { return ""; }
        return StringUtils.rightPad(StringUtils.left(chineseName,1),chineseName.length(),"*");
    }


    /**
     * 身份证号 显示最后四位，其他隐藏。共计18位或者15位
     * @param idCardNum
     * @return
     */
    public static String idCardNum(String idCardNum){
        if(StringUtils.isEmpty(idCardNum)) { return ""; }
        return StringUtils.leftPad(StringUtils.right(idCardNum,4),idCardNum.length(),"*");
    }


    /**
     * 固定电话 显示最后四位
     * @param fixedPhone
     * @return
     */
    public static String fixedPhone(String fixedPhone){
        if(StringUtils.isEmpty(fixedPhone)) { return ""; }
        return StringUtils.leftPad(StringUtils.right(fixedPhone,4),fixedPhone.length(),"*");
    }


    /**
     * 移动电话 显示前三后四   159****8369
     * @param mobilePhone
     * @return
     */
    public static String mobilePhone(String mobilePhone){
        if(StringUtils.isEmpty(mobilePhone)) { return ""; }
        String rightPad = StringUtils.rightPad(StringUtils.left(mobilePhone, 3), mobilePhone.length() - 4, "*");
        return rightPad.concat(mobilePhone.substring(7,mobilePhone.length()));
    }


    /**
     * 电子邮箱  邮箱前缀显示三位@及后面的地址显示 159********@163.com
     * @param email
     * @return
     */
    public static String email(String email){
        if(StringUtils.isEmpty(email)) { return ""; }
        int index = StringUtils.indexOf(email, "@");
        return StringUtils.rightPad(StringUtils.left(email, 3), index, "*").concat(email.substring(index,email.length()));
    }


    /**
     * 银行卡号  前六后四
     * @param bankCard
     * @return
     */
    public static String bankCard(String bankCard){
        if(StringUtils.isEmpty(bankCard)){ return ""; }

        String rightPad = StringUtils.rightPad(StringUtils.left(bankCard, 6), bankCard.length() - 4, "*");
        return rightPad.concat(bankCard.substring(15,bankCard.length()));

    }


    /**
     * 银行卡有效期   前一后一
     * @param bankCardValidData
     * @return
     */
    public static String bankCardValidDate(String bankCardValidData){
        if(StringUtils.isEmpty(bankCardValidData)){ return ""; }

        String rightPad = StringUtils.rightPad(StringUtils.left(bankCardValidData, 1), bankCardValidData.length() - 1, "*");
        return rightPad.concat(bankCardValidData.substring(15,bankCardValidData.length()));

    }


}
