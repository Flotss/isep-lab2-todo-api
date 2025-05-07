package com.isep.lab2todoapi.config;

import com.isep.lab2todoapi.application.ITodoRepository;
import com.isep.lab2todoapi.persistence.csvfiles.TodoCsvFilesRepository;
import com.isep.lab2todoapi.persistence.inmemory.TodoInMemoryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.isep.lab2todoapi.application.RepositoryType.*;


@Configuration
public class RepositoryConfig {

    @Value("${repository.type}")
    private String repositoryType;

    @Bean
    public ITodoRepository todoRepository() {
        if (INMEMORY.getValue().equalsIgnoreCase(repositoryType)) {
            return new TodoInMemoryRepository();
        }

        if (CSV.getValue().equalsIgnoreCase(repositoryType)) {
            return new TodoCsvFilesRepository();
        }

        return new TodoInMemoryRepository(); // Par défaut
    }
}
