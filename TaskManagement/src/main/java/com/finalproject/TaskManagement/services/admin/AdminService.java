package com.finalproject.TaskManagement.services.admin;

import com.finalproject.TaskManagement.dtos.CommentDTO;
import com.finalproject.TaskManagement.dtos.TaskDTO;
import com.finalproject.TaskManagement.dtos.UserDTO;

import java.util.List;

/**
 * Интерфейс для сервиса управления администрацией.
 * Определяет методы для работы с пользователями, задачами и комментариями.
 */
public interface AdminService {

    /**
     * Получает список всех пользователей.
     *
     * @return список DTO объектов пользователей
     */
    List<UserDTO> getUsers();

    /**
     * Создает новую задачу.
     *
     * @param taskDTO DTO объекта задачи
     * @return созданная задача в виде DTO
     */
    TaskDTO postTask(TaskDTO taskDTO);

    /**
     * Получает список всех задач.
     *
     * @return список DTO объектов задач
     */
    List<TaskDTO> getTasks();

    /**
     * Получает задачу по идентификатору.
     *
     * @param id идентификатор задачи
     * @return задача в виде DTO
     */
    TaskDTO getTaskById(Long id);

    /**
     * Удаляет задачу по идентификатору.
     *
     * @param id идентификатор задачи
     */
    void deleteTaskById(Long id);

    /**
     * Ищет задачи по заголовку.
     *
     * @param title подстрока для поиска
     * @return список задач, соответствующих запросу
     */
    List<TaskDTO> searchTaskByTitle(String title);

    /**
     * Обновляет задачу по идентификатору.
     *
     * @param id идентификатор задачи
     * @param taskDTO объект DTO с обновленными данными
     * @return обновленная задача в виде DTO
     */
    TaskDTO updateTask(Long id, TaskDTO taskDTO);

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
     * Получает список комментариев по идентификатору задачи.
     *
     * @param taskId идентификатор задачи
     * @return список комментариев в виде DTO
     */
    List<CommentDTO> getCommentsByTask(Long taskId);
}
