package com.finalproject.TaskManagement.dtos;

import lombok.Data;

import java.util.Date;

/**
 * Объект передачи данных (DTO), представляющий комментарий.
 * Используется для передачи данных о комментариях между различными частями приложения.
 */
@Data
public class CommentDTO {

    private Long id; // Уникальный идентификатор комментария

    private String content; // Текст комментария

    private Date createdAt; // Дата и время создания комментария

    private Long postedUserId; // Идентификатор пользователя, оставившего комментарий

    private String postedName; // Имя пользователя, оставившего комментарий

    private Long taskId; // Идентификатор задачи, к которой относится комментарий

}
