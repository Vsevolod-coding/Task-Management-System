package com.finalproject.TaskManagement.repository;

import com.finalproject.TaskManagement.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для работы с сущностью Comment.
 * Предоставляет методы для взаимодействия с базой данных.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * Ищет все комментарии, связанные с указанным идентификатором задачи.
     *
     * @param taskId идентификатор задачи
     * @return список комментариев, связанных с задачей
     */
    List<Comment> findAllByTaskId(Long taskId);

}
