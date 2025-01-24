package com.finalproject.TaskManagement.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * Утилита для работы с JWT токенами. Предоставляет методы для генерации,
 * проверки и извлечения информации из токенов.
 */
@Component
@RequiredArgsConstructor
public class JwtUtil {

    /**
     * Генерирует ключ для подписи токенов.
     *
     * @return объект Key для подписи токенов
     */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode("5AC9A8F8F5ADAB7E4A3068D4956A72BE3943253F18298AF3CC3368C8794A25D9");
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Генерирует токен с дополнительными данными.
     *
     * @param extraClaims  дополнительные данные для токена
     * @param userDetails  данные пользователя
     * @return строка токена
     */
    private String generateToken(Map<String, Objects> extraClaims, UserDetails userDetails) {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }

    /**
     * Генерирует токен для указанного пользователя.
     *
     * @param userDetails данные пользователя
     * @return строка токена
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Проверяет, является ли токен валидным для указанного пользователя.
     *
     * @param token       строка токена
     * @param userDetails данные пользователя
     * @return true, если токен валиден; false в противном случае
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Проверяет, истёк ли срок действия токена.
     *
     * @param token строка токена
     * @return true, если срок действия истёк; false в противном случае
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Извлекает дату истечения срока действия токена.
     *
     * @param token строка токена
     * @return дата истечения срока действия
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Извлекает имя пользователя (subject) из токена.
     *
     * @param token строка токена
     * @return имя пользователя
     */
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Извлекает определённое утверждение из токена.
     *
     * @param token          строка токена
     * @param claimsResolvers функция для обработки утверждения
     * @param <T>            тип утверждения
     * @return значение утверждения
     */
    private <T>T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaim(token);
        return claimsResolvers.apply(claims);
    }

    /**
     * Извлекает все утверждения из токена.
     *
     * @param token строка токена
     * @return объект Claims с данными токена
     */
    private Claims extractAllClaim(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }

}
