package com.mydiary.mydiaryprj.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mydiary.mydiaryprj.common.domain.LoginUser;
import com.mydiary.mydiaryprj.common.exception.ExceptionStatus;
import com.mydiary.mydiaryprj.common.exception.MyDiaryException;
import lombok.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

public class JwtProvider {

    public static final Long ONE_HOUR_MILLISECOND = 3_600_000L;

    public static String make(final JwtProvider.Recipe recipe, final String jwtSecret) {
        Long currentTimeMillis = System.currentTimeMillis();

        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        String jwt = JWT.create()
                .withClaim("userId", recipe.getUserId())
                .withIssuedAt(new Date(currentTimeMillis))
                .withExpiresAt(new Date(currentTimeMillis + ONE_HOUR_MILLISECOND))
                .sign(algorithm);
        return jwt;
    }

    public static void verify(final String jwt, final String jwtSecret) {
        try {

            if (jwt == null || jwt.equals("")) {
                throw new MyDiaryException(ExceptionStatus.NON_SIGN_IN);
            }
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            JWTVerifier verifier = JWT.require(algorithm).build();

            verifier.verify(jwt);

        } catch (TokenExpiredException e) {
            throw new MyDiaryException(ExceptionStatus.EXPIRED_TOKEN);
        } catch (Throwable t) {
            if (t instanceof MyDiaryException) {
                throw (MyDiaryException) t;
            } else {
                throw new MyDiaryException(ExceptionStatus.INVALID_TOKEN);
            }
        }
    }

    public static String getJwt(final HttpServletRequest hsr) {
        String authorization = hsr.getHeader("Authorization");

        String BEARER = "Bearer ";

        String jwt = authorization.substring(BEARER.length());

        return jwt;

    }

    public static LoginUser getLoginUser(final HttpServletRequest hsr) {
        String jwt = getJwt(hsr);

        Long id = JwtProvider.getUserId(jwt);

        return LoginUser.builder().id(id).build();
    }


    public static Long getUserId(final String jwt) {

        DecodedJWT decodedJWT = JWT.decode(jwt);
        //token : aaaaaaaaa = headers, bbbbbbbbbb = payload ,cccccccccc = ~signiture

        byte[] payloadBytes = decodedJWT.getPayload().getBytes();
        byte[] jsonBytes = Base64.getDecoder().decode(payloadBytes);

        String json = new String(jsonBytes);
        JwtProvider.Recipe recipe = JSON.load(json, JwtProvider.Recipe.class);

        return recipe.getUserId();
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Recipe {
        private Long userId;
        //        private List<String> Roles; 테이블을 따로 만들어야함
        private Long iat;
        private Long exp;

    }
}
