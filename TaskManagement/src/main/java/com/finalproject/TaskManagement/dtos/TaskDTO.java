package com.finalproject.TaskManagement.dtos;

import com.finalproject.TaskManagement.enums.TaskStatus;
import lombok.Data;

import java.util.Date;

/**
 * Объект передачи данных (DTO), представляющий задачу.
 * Используется для передачи данных о задаче между различными частями приложения.
 */
@Data
public class TaskDTO {

    private Long id; // id задачи

    private String title; // Заголовок задачи

    private String description; // Подробное описание задачи

    private Date dueDate; // Срок выполнения задачи

    private String priority; // Приоритет задачи (например, HIGH, MEDIUM, LOW)

    private TaskStatus taskStatus; // Текущий статус задачи (например, PENDING, COMPLETED)

    private Long employeeId; // Идентификатор сотрудника, которому назначена задача

    private String employeeName; // Имя сотрудника, которому назначена задача

}
