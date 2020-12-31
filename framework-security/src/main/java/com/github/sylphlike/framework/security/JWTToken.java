package com.github.sylphlike.framework.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.UUID;

/**
 * <p>  time 10/09/2020 18:19  星期四 (dd/MM/YYYY HH:mm) 
 * <p> email 15923508369@163.com 
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public class JWTToken {

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTToken.class);

    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };



    private static final String ISSUER = "Gopal.pan";

    private static final Algorithm algorithm = Algorithm.HMAC256("secret");




    /**
     * 签发JWT
     *
     *  Header
     *
     *  Payload
     *
     *  Signature
     * @return  String
     *
     */
    public static String create(String subject) {

        return create(subject,30L);

    }



    /**
     * <p>  time 13:28 2020/11/17 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com 
     * 签发指定过期时间的token
     * @param subject   内容
     * @param expires   过期时间，单位分钟
     * @return   java.lang.String
     * @author   Gopal.pan
     */
    public static String create(String subject,Long expires) {
        return JWT.create()
                .withKeyId(sixUuid())
                .withJWTId(sixUuid())
                .withIssuer(ISSUER)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(new Date().getTime() +  expires * 60000 ))  //默认token 失效时间为30分钟
                .withSubject(subject)
                .sign(algorithm);
    }




    /**
     * 验证token
     * @param jwtStr
     * @return
     */
    public static boolean validate(String jwtStr) {

        JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
        verifier.verify(jwtStr);
        return true;


    }



    /**
     * 验证token
     * @param jwtStr
     * @return
     */
    public static String validateSubject(String jwtStr) {
        JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
        DecodedJWT verify = verifier.verify(jwtStr);
        return verify.getSubject();
    }





    /**
     * <p>  time 15:33 2020/10/8 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com 
     *  6位随机数
     * @param
     * @return java.lang.String
     * @author Gopal.pan
     */
    public static String sixUuid() {
        StringBuilder shortBuffer = new StringBuilder();
        String uuid = UUID.randomUUID().toString();
        for (int i = 0; i < 6; i++) {
            String str = uuid.substring(i * 6, i * 6 + 6);
            int x = Integer.parseInt(str.replace("-",""), 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();

    }


}
