package com.github.sylphlike.framework.security;

import com.alipay.api.internal.util.asymmetric.RSAEncryptor;

import java.util.Map;

/**
 * <p>  time 15:23 2021/06/23  星期三 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */
public class RSATest {

    public static void main(String[] args) throws Exception {
        Map<String, String> keyMap = RSAEncrypt.genKeyPair();

        String publicKey = keyMap.get(RSAEncrypt.PUBLIC_KEY);
        String privateKey = keyMap.get(RSAEncrypt.PRIVATE_KEY);

        String data = "name=Gopal.pan&age=18&phone=15923508369&assets=89877265";
        String sign = RSAEncrypt.sign(data, privateKey);
        System.out.println(sign);

        RSAEncryptor rsaEncryptor = new RSAEncryptor();
        String sign1 = rsaEncryptor.sign(data, "UTF-8", privateKey);
        System.out.println(sign1);



    }
}
