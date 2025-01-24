package com.finalproject.TaskManagement.services.employee;

import com.finalproject.TaskManagement.dtos.CommentDTO;
import com.finalproject.TaskManagement.dtos.TaskDTO;

import java.util.List;

/**
 * Сервис для работы с задачами и комментариями для сотрудников.
 */
public interface EmployeeService {

    /**
     * Получает задачи, связанные с пользователем по его идентификатору.
     *
     * @param id идентификатор пользователя
     * @return список задач в виде DTO
     */
    List<TaskDTO> getTasksByUserId(Long id);

    /**
     * Обновляет статус задачи.
     *
     * @param id идентификатор задачи
     * @param status новый статус задачи
     * @return обновленная задача в виде DTO
     */
    TaskDTO updateTask(Long id, String status);

    /**
     * Получает задачу по идентификатору.
     *
     * @param id идентификатор задачи
     * @return задача в виде DTO
     */
    TaskDTO getTaskById(Long id);

    /**
     * Создает комментарий для задачи.
     *
     * @param taskId  идентификатор задачи
     * @param postedBy идентификатор пользователя, опубликовавшего комментарий
     * @param content содержимое комментария
     * @return созданный комментарий в виде DTO
     */
    CommentDTO createComment(Long taskId, Long postedBy, String content);

    /**
     * Получает комментарии для задачи.
     *
     * @param taskId идентификатор задачи
     * @return список комментариев в виде DTO
     */
    List<CommentDTO> getCommentsByTask(Long taskId);
}
