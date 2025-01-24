package com.finalproject.TaskManagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.finalproject.TaskManagement.dtos.CommentDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

/**
 * Сущность, представляющая комментарий в системе.
 * Связана с задачей и пользователем, оставившим комментарий.
 */
@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Уникальный идентификатор комментария

    private String content; // Текст комментария

    private Date createdAt; // Дата и время создания комментария

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Task task; // Задача, к которой относится комментарий

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user; // Пользователь, оставивший комментарий

    /**
     * Преобразует сущность Comment в объект CommentDTO.
     *
     * @return объект CommentDTO, содержащий данные комментария
     */
    public CommentDTO getCommentDTO() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(id);
        commentDTO.setContent(content);
        commentDTO.setCreatedAt(createdAt);
        commentDTO.setPostedUserId(user.getId());
        commentDTO.setPostedName(user.getName());
        commentDTO.setTaskId(task.getId());
        return commentDTO;
    }

}
