package com.finalproject.TaskManagement.dtos;

import com.finalproject.TaskManagement.enums.UserRole;
import lombok.Data;

/**
 * Объект ответа, используемый для авторизации.
 * Возвращает данные о JWT-токене и информацию о пользователе после успешного входа в систему.
 */
@Data
public class AuthResponse {

    private String jwt; // JWT-токен для аутентификации

    private Long userId; // Идентификатор пользователя

    private UserRole userRole; // Роль пользователя в системе

}
