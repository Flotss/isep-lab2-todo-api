package com.isep.lab2todoapi.persistence.inmemory;

import com.isep.lab2todoapi.Todo;
import com.isep.lab2todoapi.application.ITodoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoInMemoryRepository implements ITodoRepository {
    private final static List<Todo> todos = new ArrayList<>();

    @Override
    public void addTodo(Todo todo) {
        todos.add(todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todos;
    }
}
