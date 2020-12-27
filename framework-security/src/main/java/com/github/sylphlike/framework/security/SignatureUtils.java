package com.github.sylphlike.framework.security;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * <p>  time 10/09/2020 18:19  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * 加签与验签工具类
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public class SignatureUtils {


    /**
     * 创建签名
     * @param signKey            签名key
     * @param filterParams       需要过滤的参数名称(可以为多个参数名称，使用字符串拼接，如果是签名参数名称则也需加入到过滤字符串中，因为验签时)
     * @param params             参数
     * @param securityTypeEnums  签名方式 {@link SecurityTypeEnums}
     * @param isClient           是否是客户端(针对 RSA 方式创建签名有效)
     * @return
     */
    public static String createSignature(String signKey, String filterParams, Map<String,String> params,
                                         SecurityTypeEnums securityTypeEnums, boolean isClient) throws Exception {
        String singData = getSignDateStr(filterParams,params);
        if (SecurityTypeEnums.MD5.equals(securityTypeEnums)){
            return Md5Utils.md5(singData + signKey);
        } else if(SecurityTypeEnums.AES.equals(securityTypeEnums)){
            return AESEncryptUtils.encrypt(Md5Utils.md5(singData),signKey);

        } else if(SecurityTypeEnums.RSA.equals(securityTypeEnums)){
            if(isClient){
                return RSAEncryptUtils.encryptByPublicKey(Md5Utils.md5(singData),signKey);
            }else {
                return RSAEncryptUtils.encryptByPrivateKey(Md5Utils.md5(singData),signKey);
            }

        } else{
            throw new SecurityException("无效签名方法");
        }
    }


    /**
     * 验证签名
     * @param signKey            签名key
     * @param filterParams       签名参数名称(可以为多个参数名称)
     * @param params             参数
     * @param securityTypeEnums       签名方式 {@link SecurityTypeEnums}
     * @param signParamName      签名参数的名称
     * @param isClient           是否是客户端(针对 RSA 方式创建签名有效)
     * @return
     */
    public static boolean verifySignature(String signKey, String filterParams, Map<String,String> params,
                                          SecurityTypeEnums securityTypeEnums, String signParamName, boolean isClient) throws Exception {
        String signData = getSignDateStr(filterParams,params);
        String signValue = params.get(signParamName);
        if (SecurityTypeEnums.MD5.equals(securityTypeEnums)){
            return signValue.equals(Md5Utils.md5(signData + signKey));
        }else if(SecurityTypeEnums.AES.equals(securityTypeEnums)){
            return signValue.equals(AESEncryptUtils.encrypt(Md5Utils.md5(signData),signKey));
        }else if(SecurityTypeEnums.RSA.equals(securityTypeEnums)){
            if(isClient){
                return Md5Utils.md5(signData).equals(RSAEncryptUtils.decryptByPublicKey(signValue,signKey));
            }else {
                return Md5Utils.md5(signData).equals(RSAEncryptUtils.decryptByPrivateKey(signValue,signKey));
            }
        }else {
            throw new SecurityException("无效签名方法");
        }

    }


    private static String getSignDateStr(String filterParams, Map<String, String> params) {

        List<String> paramsList = Lists.newArrayList(params.keySet());
        Collections.sort(paramsList);
        StringBuilder stringBuilder = new StringBuilder();
        for (String key: paramsList) {
            if(filterParams.equals(key)){
                continue;
            }
            String value = params.get(key);
            if(null == value){
                stringBuilder.append("&").append(key).append("=");
            }else {
                stringBuilder.append("&").append(key).append("=").append(value);
            }

        }
        return StringUtils.removeStart(stringBuilder.toString(),"&");


    }





}
