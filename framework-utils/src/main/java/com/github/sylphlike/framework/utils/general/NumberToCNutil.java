package com.github.sylphlike.framework.utils.general;

import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 * 阿拉伯数字转中文大写
 * <p>  time 17:56 2018/12/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */


public class NumberToCNutil {

    /**
     * 汉语中数字大写
     */
    private static final String[] CN_UPPER_NUMBER = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
    /**
     * 汉语中货币单位大写
     */
    private static final String[] CN_UPPER_MONEY_UNIT = { "分", "角", "元", "拾", "佰", "仟", "万", "拾", "佰", "仟",
            "亿", "拾", "佰", "仟", "兆", "拾", "佰", "仟" };
    /**
     * 特殊字符：整
     */
    private static final String CN_FULL = "整";
    /**
     * 特殊字符：负
     */
    private static final String CN_NEGATIVE = "负";
    /**
     * 金额的精度，默认值为2
     */
    private static final int MONEY_PRECISION = 2;
    /**
     * 特殊字符：零元整
     */
    private static final String CN_ZERO_FULL = "零元" + CN_FULL;

    /**
     * 把输入的金额转换为汉语中人民币的大写
     * @param numberOfMoney   输入的金额
     * @return 对应的汉语大写
     */
    public static String number2CNMoneyUnit(BigDecimal numberOfMoney) {
        StringBuilder buffer = new StringBuilder();

        int signum = numberOfMoney.signum();
        if (signum == 0) {   return CN_ZERO_FULL; }
        long number = numberOfMoney.movePointRight(MONEY_PRECISION).setScale(0, RoundingMode.CEILING).abs().longValue();
        long scale = number % 100;
        int numUnit = 0;
        int numIndex = 0;
        boolean getZero = false;
        if (!(scale > 0)) {
            numIndex = 2;
            number = number / 100;
            getZero = true;
        }
        if ((scale > 0) && (!(scale % 10 > 0))) {
            numIndex = 1;
            number = number / 10;
            getZero = true;
        }
        int zeroSize = 0;
        while (number > 0) {
            // 每次获取到最后一个数
            numUnit = (int) (number % 10);
            if (numUnit > 0) {
                if ((numIndex == 9) && (zeroSize >= 3)) {
                    buffer.insert(0, CN_UPPER_MONEY_UNIT[6]);
                }
                if ((numIndex == 13) && (zeroSize >= 3)) {
                    buffer.insert(0, CN_UPPER_MONEY_UNIT[10]);
                }
                buffer.insert(0, CN_UPPER_MONEY_UNIT[numIndex]);
                buffer.insert(0, CN_UPPER_NUMBER[numUnit]);
                getZero = false;
                zeroSize = 0;
            } else {
                ++zeroSize;
                if (!(getZero)) {
                    buffer.insert(0, CN_UPPER_NUMBER[numUnit]);
                }
                if (numIndex == 2) {
                    buffer.insert(0, CN_UPPER_MONEY_UNIT[numIndex]);
                } else if (((numIndex - 2) % 4 == 0) && (number % 1000 > 0)) {
                    buffer.insert(0, CN_UPPER_MONEY_UNIT[numIndex]);
                }
                getZero = true;
            }
            // 让number每次都去掉最后一个数
            number = number / 10;
            ++numIndex;
        }
        if (signum == -1) {
            buffer.insert(0, CN_NEGATIVE);
        }
        // 输入的数字小数点后两位为"00"的情况，则要在最后追加特殊字符：整
        if (!(scale > 0)) {
            buffer.append(CN_FULL);
        }
        return buffer.toString();
    }
}
