package com.finalproject.TaskManagement.controllers.admin;

import com.finalproject.TaskManagement.dtos.CommentDTO;
import com.finalproject.TaskManagement.dtos.TaskDTO;
import com.finalproject.TaskManagement.services.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// AdminController: Контроллер для управления задачами и пользователями
@CrossOrigin("http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    // Сервис для обработки логики администратора
    private final AdminService adminService;

    // Получение списка всех пользователей
    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(adminService.getUsers());
    }

    // Добавление новой задачи
    @PostMapping("/task")
    public ResponseEntity<?> postTask(@RequestBody TaskDTO taskDTO) {
        TaskDTO createdtaskDTO = adminService.postTask(taskDTO);
        if (createdtaskDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdtaskDTO);
    }

    // Получение всех задач
    @GetMapping("/tasks")
    public ResponseEntity<?> getTasks() {
        return ResponseEntity.ok(adminService.getTasks());
    }

    // Получение задачи по ID
    @GetMapping("/task/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getTaskById(id));
    }

    // Удаление задачи по ID
    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteTaskById(@PathVariable Long id) {
        adminService.deleteTaskById(id);
        return ResponseEntity.ok(null);
    }

    // Поиск задач по заголовку
    @GetMapping("/tasks/search/{title}")
    public ResponseEntity<?> searchTaskByTitle(@PathVariable String title) {
        return ResponseEntity.ok(adminService.searchTaskByTitle(title));
    }

    // Обновление задачи по ID
    @PutMapping("/task/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        TaskDTO updatedTaskDTO = adminService.updateTask(id, taskDTO);
        if (updatedTaskDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedTaskDTO);
    }

    // Создание комментария для задачи
    @PostMapping("/task/comment")
    public ResponseEntity<?> createComment(@RequestParam Long taskId, @RequestParam Long postedBy, @RequestBody String content) {
        CommentDTO createdCommentDTO = adminService.createComment(taskId, postedBy, content);
        if (createdCommentDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCommentDTO);
    }

    // Получение всех комментариев для задачи по ID задачи
    @GetMapping("/task/{taskId}/comments")
    public ResponseEntity<?> getCommentsByTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(adminService.getCommentsByTask(taskId));
    }
}
