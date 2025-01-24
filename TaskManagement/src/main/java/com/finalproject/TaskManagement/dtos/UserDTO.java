package com.finalproject.TaskManagement.dtos;

import com.finalproject.TaskManagement.enums.UserRole;
import lombok.Data;

/**
 * Объект передачи данных (DTO), представляющий пользователя.
 * Используется для передачи данных о пользователе между различными частями приложения.
 */
@Data
public class UserDTO {

    private Long id; // Уникальный идентификатор пользователя

    private String name; // Имя пользователя

    private String email; // Электронная почта пользователя

    private String password; // Зашифрованный пароль пользователя

    private UserRole userRole; // Роль пользователя в системе (ADMIN или EMPLOYEE)
}
