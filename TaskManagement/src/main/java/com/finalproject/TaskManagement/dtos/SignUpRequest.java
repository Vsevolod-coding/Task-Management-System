package com.finalproject.TaskManagement.dtos;

import lombok.Data;

/**
 * Объект запроса, используемый для регистрации пользователя.
 * Содержит данные, необходимые для создания новой учетной записи.
 */
@Data
public class SignUpRequest {

    private String email; // Электронная почта нового пользователя

    private String password; // Пароль нового пользователя

    private String name; // Имя нового пользователя

}
