package com.github.sylphlike.framework.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */

public class JWTTokenUtils {

    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };



    private static final String ISSUER = "Gopal.pan";

    private static final Algorithm algorithm = Algorithm.HMAC256("secret");



    /**
     * 签发JWT 默认token失效时间为30分钟
     * <p>  time 16:37 2021/4/21      </p>
     * <p> email 15923508369@163.com  </p>
     * @param subject  内容
     * @return  java.lang.String
     * @author  Gopal.pan
     */
    public static String create(String subject) {

        return create(subject,30L, TimeUnit.MINUTES);

    }



    /**
     * 签发指定过期时间的token
     * <p>  time 16:37 2021/4/21      </p>
     * <p> email 15923508369@163.com  </p>
     * @param subject   内容
     * @param expires   过期时间
     * @param timeUnit  时间单位
     * @return  java.lang.String
     * @author  Gopal.pan
     */
    public static String create(String subject, Long expires, TimeUnit timeUnit) {
        long millisExpires;
        if (!TimeUnit.MILLISECONDS.equals(timeUnit)){
            millisExpires = timeUnit.toMillis(expires);
        }else {
            millisExpires = expires;
        }

        return JWT.create()
                .withKeyId(sixUuid())
                .withJWTId(sixUuid())
                .withIssuer(ISSUER)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(new Date().getTime() +  millisExpires ))
                .withSubject(subject)
                .sign(algorithm);
    }



    /**
     * 验证token
     * <p>  time 16:38 2021/4/21      </p>
     * <p> email 15923508369@163.com  </p>
     * @param jwtStr  待验证token
     * @return  boolean
     * @author  Gopal.pan
     */
    public static boolean validate(String jwtStr) {

        JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
        verifier.verify(jwtStr);
        return true;


    }



    /**
     * 验证token
     * <p>  time 16:38 2021/4/21      </p>
     * <p> email 15923508369@163.com  </p>
     * @param jwtStr  待验证token
     * @return  java.lang.String
     * @author  Gopal.pan
     */
    public static String validateSubject(String jwtStr) {
        JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
        DecodedJWT verify = verifier.verify(jwtStr);
        return verify.getSubject();
    }




    /**
     * 6位随机数
     * <p>  time 16:39 2021/4/21      </p>
     * <p> email 15923508369@163.com  </p>
     * @return  java.lang.String
     * @author  Gopal.pan
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
