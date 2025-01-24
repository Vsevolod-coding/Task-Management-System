package com.finalproject.TaskManagement.configs;

import com.finalproject.TaskManagement.enums.UserRole;
import com.finalproject.TaskManagement.services.jwt.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    // Сервис для работы с пользователями
    private final UserService userService;

    // Фильтр для обработки JWT-токенов
    private final JwtAuthFilter jwtAuthFilter;

    /**
     * Конфигурирует цепочку фильтров безопасности.
     * @param security Объект конфигурации безопасности
     * @return Сконфигурированная цепочка фильтров
     * @throws Exception Исключение конфигурации
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security
                .csrf(AbstractHttpConfigurer::disable) // Отключение защиты от CSRF
                .cors(Customizer.withDefaults()) // Включение поддержки CORS
                .authorizeHttpRequests(request -> request.requestMatchers("/api/auth/**").permitAll() // Разрешить доступ к аутентификации для всех
                    .requestMatchers("/api/admin/**").hasAnyAuthority(UserRole.ADMIN.name()) // Доступ только для администраторов
                    .requestMatchers("/api/employee/**").hasAnyAuthority(UserRole.EMPLOYEE.name()) // Доступ только для сотрудников
                    .anyRequest().authenticated() // Остальные запросы требуют аутентификации
                )
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Отключение сессий
                .authenticationProvider(authenticationProvider()) // Установка провайдера аутентификации
                .addFilterBefore(
                        jwtAuthFilter, UsernamePasswordAuthenticationFilter.class // Добавление JWT-фильтра
                );
        return security.build();
    }

    /**
     * Создает кодировщик паролей.
     * @return BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Настраивает провайдер аутентификации с использованием UserService.
     * @return Провайдер аутентификации
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService.userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * Создает менеджер аутентификации.
     * @param configuration Конфигурация аутентификации
     * @return Менеджер аутентификации
     * @throws Exception Исключение конфигурации
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
