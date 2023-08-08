package com.mydiary.mydiaryprj.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mydiary.mydiaryprj.common.exception.ExceptionStatus;
import com.mydiary.mydiaryprj.common.exception.MyDiaryException;
import lombok.*;

import java.util.List;

public class JwtProvider {

    public static final Long ONE_HOUR_MILLISECOND = 3_600_000L;
    public static String make(final JwtProvider.Recipe recipe, final String jwtSecret){
        Long currentTimeMillis = System.currentTimeMillis();

        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        String jwt = JWT.create()
                .withClaim("userId", recipe.getId())
                .withClaim("issuedAt", currentTimeMillis)
                .withClaim("expiresAt", currentTimeMillis + ONE_HOUR_MILLISECOND)
                .sign(algorithm);
        return jwt;
    }

    public static String verify(final String jwt, final String jwtSecret){
        try {

            if (jwt == null || jwt.equals("")) {
                throw new MyDiaryException(ExceptionStatus.NON_SIGN_IN);
            }
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            JWTVerifier verifier = JWT.require(algorithm).build();

            verifier.verify(jwt);

            DecodedJWT decodedJWT= JWT.decode(jwt);




        }catch (Throwable t){
            if(t instanceof MyDiaryException ){
                throw (MyDiaryException) t;
            }else {
                throw new MyDiaryException(ExceptionStatus.INVALID_TOKEN);
            }
        }
    }



    @Getter @Setter
    @Builder @AllArgsConstructor
    @NoArgsConstructor
    public static class Recipe{
        private Long id;
//        private List<String> Roles; 테이블을 따로 만들어야함
        private Long issuedAt;
        private Long expiresAt;

    }
}
