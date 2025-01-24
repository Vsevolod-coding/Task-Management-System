package com.finalproject.TaskManagement.entities;

import com.finalproject.TaskManagement.dtos.UserDTO;
import com.finalproject.TaskManagement.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Сущность, представляющая пользователя системы.
 * Хранит данные о пользователе и реализует интерфейс UserDetails для работы с Spring Security.
 */
@Entity
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private UserRole userRole;

    /**
     * Преобразует сущность User в объект UserDTO.
     *
     * @return объект UserDTO, содержащий данные пользователя
     */
    public UserDTO getUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setName(name);
        userDTO.setEmail(email);
        userDTO.setUserRole(userRole);
        return userDTO;
    }

    // Возвращает полномочия пользователя
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userRole.name()));
    }

    // Возвращает электронную почту как имя пользователя
    @Override
    public String getUsername() {
        return email;
    }

    // Указывает, что учетная запись не просрочена
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Указывает, что учетная запись не заблокирована
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Указывает, что учетные данные не просрочены
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Указывает, что учетная запись активна
    @Override
    public boolean isEnabled() {
        return true;
    }
}
