package com.github.sylphlike.framework.utils;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberToCarrierMapper;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;

import java.util.Locale;

/**
 * <p>  time 15:14 2021/05/31  星期一 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */
public class LibphonenumberTest {

    public static void main(String[] args) {
        //初始化手机号
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber referencePhonenumber = null;
        try {
            referencePhonenumber = phoneUtil.parse("15923508369", "CN");
        } catch (NumberParseException e) {
        }
        // 营运商
        PhoneNumberToCarrierMapper carrierMapper = PhoneNumberToCarrierMapper.getInstance();
        String carrier = carrierMapper.getNameForNumber(referencePhonenumber, Locale.CHINA);
        System.out.println(carrier);

        // 归属地
        PhoneNumberOfflineGeocoder phoneNumberOfflineGeocoder = PhoneNumberOfflineGeocoder.getInstance();
        String city = phoneNumberOfflineGeocoder.getDescriptionForNumber(referencePhonenumber, Locale.CHINA);
        System.out.println(city);
    }
}
