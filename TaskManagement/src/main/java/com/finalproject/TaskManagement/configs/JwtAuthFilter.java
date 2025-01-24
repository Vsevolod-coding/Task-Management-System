package com.finalproject.TaskManagement.configs;

import com.finalproject.TaskManagement.services.jwt.UserService;
import com.finalproject.TaskManagement.utils.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    // Утилита для работы с JWT-токенами
    private final JwtUtil jwtUtil;

    // Сервис для получения информации о пользователях
    private final UserService userService;

    /**
     * Фильтрует запросы, извлекая JWT-токен из заголовка Authorization.
     * Если токен валидный, добавляет аутентификацию пользователя в SecurityContext.
     *
     * @param request     HTTP-запрос
     * @param response    HTTP-ответ
     * @param filterChain Цепочка фильтров
     * @throws ServletException Исключение, связанное с Servlet
     * @throws IOException      Исключение ввода-вывода
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        // Извлечение заголовка Authorization
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // Проверка наличия заголовка и его корректности
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
            filterChain.doFilter(request, response); // Пропуск запроса дальше по цепочке
            return;
        }

        // Извлечение токена из заголовка
        jwt = authHeader.substring(7);

        // Извлечение email пользователя из токена
        userEmail = jwtUtil.extractUserName(jwt);

        // Проверка, что email получен и пользователь еще не аутентифицирован
        if (StringUtils.isNoneEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Загрузка данных пользователя
            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);

            // Проверка валидности токена
            if (jwtUtil.isTokenValid(jwt, userDetails)) {

                // Создание нового SecurityContext
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

                // Создание объекта аутентификации
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Установка аутентификации в SecurityContext
                securityContext.setAuthentication(authenticationToken);
                SecurityContextHolder.setContext(securityContext);
            }
        }

        // Пропуск запроса дальше по цепочке фильтров
        filterChain.doFilter(request, response);
    }
}

