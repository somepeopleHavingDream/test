package org.yangxin.test.security.jwt.util;

import cn.hutool.crypto.asymmetric.RSA;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

/**
 * @author yangxin
 * 2022/8/24 9:59
 */
@SuppressWarnings("unused")
public class JwtUtil {

    /**
     * 签名密钥
     */
    private static final String SECRET = "!DAR$";

    private static final String RSA_PRIVATE_KEY = "...";
    private static final String RSA_PUBLIC_KEY = "...";

    /**
     * 生成token，
     * 默认token的过期时间为7天
     *
     * @param payload token携带的信息
     * @return token字符串
     */
    public static String getToken(Map<String, String> payload) {
        JWTCreator.Builder builder = JWT.create();

        // 构建payload
        payload.forEach(builder::withClaim);
        // 指定过期时间和签名算法
        return builder.withExpiresAt(LocalDateTime.now().plusDays(7).toInstant(ZoneOffset.ofHours(8)))
                .sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 生成token，
     * 默认token的过期时间为7天
     *
     * @param payload token携带的信息
     * @return token字符串
     */
    public static String getTokenRsa(Map<String, String> payload) {
        JWTCreator.Builder builder = JWT.create();

        // 构建payload
        payload.forEach(builder::withClaim);

        // 利用hutool创建RSA
        RSA rsa = new RSA(RSA_PRIVATE_KEY, null);
        // 获取私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) rsa.getPrivateKey();
        // 签名时传入私钥
        return builder.withExpiresAt(LocalDateTime.now().plusDays(7).toInstant(ZoneOffset.ofHours(8)))
                .sign(Algorithm.RSA256(null, privateKey));
    }

    /**
     * 解析token
     *
     * @param token token
     * @return 解析后的token
     */
    public static DecodedJWT decode(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        return verifier.verify(token);
    }

    /**
     * 解析token
     *
     * @param token token字符串
     * @return 解析后的token
     */
    public static DecodedJWT decodeRsa(String token) {
        // 利用hutool创建RSA
        RSA rsa = new RSA(null, RSA_PUBLIC_KEY);
        // 获取RSA公钥
        RSAPublicKey publicKey = (RSAPublicKey) rsa.getPublicKey();
        // 验签时传入公钥
        JWTVerifier verifier = JWT.require(Algorithm.RSA256(publicKey, null)).build();
        return verifier.verify(token);
    }
}
