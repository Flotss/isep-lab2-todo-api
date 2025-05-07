package com.isep.lab2todoapi.application;

import com.isep.lab2todoapi.Todo;
import com.isep.lab2todoapi.persistence.inmemory.TodoInMemoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TodoManager {
    ITodoRepository todoRepository = new TodoInMemoryRepository();

    public void addTodo(Todo todo) {
        todoRepository.addTodo(todo);
    }

    public List<Todo> GetAllTodos() {
        return todoRepository.getAllTodos();
    }
}
