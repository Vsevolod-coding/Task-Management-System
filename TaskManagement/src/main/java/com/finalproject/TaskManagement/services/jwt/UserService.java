package com.finalproject.TaskManagement.services.jwt;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Интерфейс для сервиса управления пользователями.
 * Определяет методы для работы с деталями пользователей.
 */
public interface UserService {

    /**
     * Предоставляет реализацию UserDetailsService для аутентификации и авторизации.
     *
     * @return объект UserDetailsService
     */
    UserDetailsService userDetailsService();

}
