package com.finalproject.TaskManagement.repository;

import com.finalproject.TaskManagement.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для работы с сущностью Task.
 * Предоставляет методы для взаимодействия с базой данных.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Ищет задачи, заголовки которых содержат указанную подстроку.
     *
     * @param title подстрока, которую нужно искать в заголовках задач
     * @return список задач, содержащих подстроку в заголовке
     */
    List<Task> findAllByTitleContaining(String title);

    /**
     * Ищет все задачи, связанные с указанным идентификатором пользователя.
     *
     * @param id идентификатор пользователя
     * @return список задач, связанных с пользователем
     */
    List<Task> findAllByUserId(Long id);
}
