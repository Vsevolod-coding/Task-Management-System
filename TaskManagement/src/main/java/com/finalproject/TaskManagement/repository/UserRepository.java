package com.finalproject.TaskManagement.repository;

import com.finalproject.TaskManagement.entities.User;
import com.finalproject.TaskManagement.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Репозиторий для работы с сущностью User.
 * Предоставляет методы для взаимодействия с базой данных.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Ищет пользователя по роли.
     *
     * @param userRole роль пользователя
     * @return объект Optional, содержащий пользователя, если найден
     */
    Optional<User> findByUserRole(UserRole userRole);

    /**
     * Ищет первого пользователя с указанной электронной почтой.
     *
     * @param email электронная почта пользователя
     * @return объект Optional, содержащий пользователя, если найден
     */
    Optional<User> findFirstByEmail(String email);
}
