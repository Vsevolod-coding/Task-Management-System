package com.finalproject.TaskManagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.finalproject.TaskManagement.dtos.TaskDTO;
import com.finalproject.TaskManagement.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

/**
 * Сущность, представляющая задачу в системе.
 * Связана с пользователем, которому назначена.
 */
@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Уникальный идентификатор задачи

    private String title; // Заголовок задачи

    private Date dueDate; // Срок выполнения задачи

    private String description; // Подробное описание задачи

    private String priority; // Приоритет задачи (CRITICAL, HIGH, MEDIUM, LOW)

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;// Пользователь, которому назначена задача

    private TaskStatus taskStatus; // Статус задачи (например, PENDING, COMPLETED)

    /**
     * Преобразует сущность Task в объект TaskDTO.
     *
     * @return объект TaskDTO, содержащий данные задачи
     */
    public TaskDTO getTaskDTO() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(id);
        taskDTO.setTitle(title);
        taskDTO.setTaskStatus(taskStatus);
        taskDTO.setEmployeeName(user.getName());
        taskDTO.setEmployeeId(user.getId());
        taskDTO.setDueDate(dueDate);
        taskDTO.setPriority(priority);
        taskDTO.setDescription(description);
        return taskDTO;
    }

}
