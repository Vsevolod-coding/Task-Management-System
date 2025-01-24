package com.finalproject.TaskManagement.dtos;

import lombok.Data;
/**
 * Объект запроса, используемый для авторизации.
 * Содержит данные, необходимые для входа в систему.
 */

@Data
public class AuthRequest {

    private String email; // Электронная почта пользователя

    private String password; // Пароль пользователя

}
