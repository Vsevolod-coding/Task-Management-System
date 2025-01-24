package com.finalproject.TaskManagement.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Настраивает разрешения CORS для API.
     * @param registry Реестр CORS-настроек
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // Разрешение CORS для всех URL, начинающихся с /api/
                .allowedOrigins("http://localhost:4200")  // Разрешить запросы с указанного URL (фронтенда)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Разрешённые HTTP-методы
                .allowedHeaders("*") // Разрешить любые заголовки
                .allowCredentials(true);  // Включить поддержку учетных данных (cookies)
    }
}