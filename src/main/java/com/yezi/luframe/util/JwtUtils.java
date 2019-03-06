package com.yezi.luframe.util;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * JSON Web Token工具类
 *
 * @author yezi
 * @date 2019/2/25 10:43
 */
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    private static final String SECRET = "XX#^hj$&%()(#*!()!KL<><Mfdgfd43&^$#KHHHGSKHJ6245djw}:f<cvb.[HTE+";

    private static final String EXP = "exp";

    private static final String PAYLOAD = "payload";

    /**
     * get jwt String of object
     *
     * @param object the POJO object
     * @param maxAge the milliseconds of life time
     * @return the jwt token
     */
    public static <T> String sign(T object, long maxAge) {
        try {
            final JWTSigner signer = new JWTSigner(SECRET);
            final Map<String, Object> claims = new HashMap<String, Object>();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(object);
            claims.put(PAYLOAD, jsonString);
            claims.put(EXP, System.currentTimeMillis() + maxAge);
            return signer.sign(claims);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * get the object of jwt if not expired
     *
     * @param jwt
     * @return POJO object
     */
    public static <T> T unsign(String jwt, Class<T> classT) {
        final JWTVerifier verifier = new JWTVerifier(SECRET);
        try {
            final Map<String, Object> claims = verifier.verify(jwt);
            if (claims.containsKey(EXP) && claims.containsKey(PAYLOAD)) {
                long exp = (Long) claims.get(EXP);
                long currentTimeMillis = System.currentTimeMillis();
                if (exp > currentTimeMillis) {
                    String json = (String) claims.get(PAYLOAD);
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.readValue(json, classT);
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }


    public static void main(String[] args) {
//        System.out.println(sign("ladsfdsflal",10000000));
        String s = unsign("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1MjQ3MjE3MDE0NDEsInBheWxvYWQiOiJcImxhZHNmZHNmbGFsXCIifQ.UUVrBDYzHrYwvvStaMoQxMroReaHTA4Ove5PkWbVx0I",
                String.class);
        System.out.println(s);
    }
}
