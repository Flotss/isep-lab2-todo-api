package com.isep.lab2todoapi.persistence.inmemory;

import com.isep.lab2todoapi.Todo;
import com.isep.lab2todoapi.application.ITodoRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TodoInMemoryRepository implements ITodoRepository {
    private final static Set<Todo> todos = new HashSet<>();

    @Override
    public void addTodo(Todo todo) {
        if (todos.contains(todo)) {
            throw new IllegalArgumentException("Todo already exists");
        }
        todos.add(todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todos.stream().toList();
    }
}
