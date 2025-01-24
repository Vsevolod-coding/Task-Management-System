package com.finalproject.TaskManagement.services.auth;

import com.finalproject.TaskManagement.dtos.SignUpRequest;
import com.finalproject.TaskManagement.dtos.UserDTO;

/**
 * Интерфейс для сервиса аутентификации.
 * Определяет методы для работы с регистрацией и проверкой пользователей.
 */
public interface AuthService {

    /**
     * Регистрирует нового пользователя.
     *
     * @param signUpRequest объект с данными для регистрации
     * @return зарегистрированный пользователь в виде DTO
     */
    UserDTO signUp(SignUpRequest signUpRequest);

    /**
     * Проверяет, существует ли пользователь с указанным email.
     *
     * @param email email для проверки
     * @return true, если пользователь с таким email существует, иначе false
     */
    Boolean hasUserThisEmail(String email);

}
