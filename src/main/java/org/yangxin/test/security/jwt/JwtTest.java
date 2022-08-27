package org.yangxin.test.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;

/**
 * JWT结构：Header、Payload、Signature
 * JWTString = Base64(Header).Base64(Payload).HMACSHA256(base64UrlEncode(header) + "." + base64UrlEncode(payload), secret)
 *
 * @author yangxin
 * 2022/8/19 11:44
 */
@SuppressWarnings("unused")
public class JwtTest {

    public static void main(String[] args) {
//        test1();
        test2();
    }

    /**
     * 解析JWT字符串，
     * 运行后如果发现报异常，大概率的原因是token已经过期
     */
    private static void test2() {
        // 创建解析对象，使用的算法和secret要与创建token时保持一致
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("!34ADAS")).build();
        // 解析指定的token
        DecodedJWT decodedJwt = verifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6Inlhbmd4aW4iLCJleHAiOjE2NjEzMDYzMzIsInVzZXJJZCI6MjF9.3FcXLLn8_m3V-_itmZujtlUogmMw4ybJrmzbPluX68s");

        // 获取解析后的token中的payload信息
        Claim userId = decodedJwt.getClaim("userId");
        Claim userName = decodedJwt.getClaim("userName");
        System.out.println(userId.asInt());
        System.out.println(userName.asString());

        // 输出超时时间
        System.out.println(decodedJwt.getExpiresAt());
    }

    /**
     * 生成JWT的token
     */
    private static void test1() {
        // 指定token过期时间为30秒
        String token = JWT.create()
                // header
                .withHeader(Collections.emptyMap())
                // payload
                .withClaim("userId", 21)
                .withClaim("userName", "yangxin")
                .withExpiresAt(LocalDateTime.now().plusSeconds(30).toInstant(ZoneOffset.ofHours(8)))
                .sign(Algorithm.HMAC256("!34ADAS"));
        System.out.println(token);
    }
}
